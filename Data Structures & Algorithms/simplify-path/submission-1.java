class Solution {
    public String simplifyPath(String path) {
        if(path == null) return null;
        if(path == "") return "";

        final Stack<String> stack = new Stack<>();

        int left = 0;
        int right = 0;
        String processing = "";
        for(; right < path.length(); ++right) {
            if(path.charAt(right) == '/') {
                if(processing.equals("slashes")) continue;

                if(processing.equals("file")) {
                    handleFile(stack, path.substring(left, right));
                }

                stack.push("/");
                processing = "slashes";
                continue;
            }

            if(processing.equals("file")) continue;

            processing = "file";
            left = right; //Start a new string tracking
        }

        //Process the last segment if any
        if(processing.equals("file")) {
            handleFile(stack, path.substring(left, right));
        }

        //Remove any dangling slash
        if(stack.size() > 1 && stack.peek().equals("/")) {
            stack.pop();
        }

        //Form and return string
        if(stack.isEmpty()) return "/";
        return stack.stream().collect(Collectors.joining(""));
    }

    private void handleFile(Stack stack, String toProcess) {
        if(toProcess.equals(".")) {
            if(!stack.isEmpty()) {
                stack.pop(); //This should pop the previous slash(/). We will add one below while processing the current slash
            } 
        } else if(toProcess.equals("..")) {
            if(!stack.isEmpty()) stack.pop(); //This should pop /
            if(!stack.isEmpty()) stack.pop(); //This should pop previous directory
            if(!stack.isEmpty()) stack.pop(); //This should pop the slash separator for the previous dir's parent; We will add one below
        } else {
            stack.push(toProcess);
        }
    }
}