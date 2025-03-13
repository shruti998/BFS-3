//Problem1
// Time Complexity :O(2^N * N)
// Space Complexity :O(N)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

class Solution {
    
    // Method to check if a given string has valid parentheses
    public boolean isValid(String s) {
        int res = 0;  // Counter to track balance of parentheses

        for (int i = 0; i < s.length(); i++) {
            // If the current character is a closing parenthesis
            if (s.charAt(i) == ')') {
                res--;  // Decrease the balance count
                if (res < 0) return false;  // If more ')' than '(' at any point, return false
            }
            // If the current character is an opening parenthesis
            else if (s.charAt(i) == '(') {
                res++;  // Increase the balance count
            }
        }
        
        // If res == 0, parentheses are balanced, otherwise they are not
        return res == 0;
    }

    // Method to remove the minimum number of invalid parentheses to make the string valid
    public List<String> removeInvalidParentheses(String s) {
        Queue<String> q = new LinkedList<>();  // Queue for BFS traversal
        Set<String> set = new HashSet<>();     // Set to track visited strings
        List<String> res = new ArrayList<>();  // List to store valid expressions
        boolean flag = false;                  // Flag to stop processing deeper levels once a valid string is found

        set.add(s);  // Add original string to the visited set
        q.add(s);    // Start BFS with the original string

        // BFS traversal: process strings level by level
        while (!q.isEmpty() && !flag) {
            int n = q.size();  // Number of elements at current level
            
            for (int i = 0; i < n; i++) {
                String cur = q.poll();  // Get the current string

                // If the string is a valid parentheses expression, add to result
                if (isValid(cur)) {
                    res.add(cur);
                    flag = true;  // Set flag to true to stop further processing at deeper levels
                }
                // If a valid string has not been found yet, generate new strings by removing one character at a time
                else if (!flag) {
                    for (int j = 0; j < cur.length(); j++) {
                        char c = cur.charAt(j);

                        // Ignore characters other than '(' and ')'
                        if (c >= 'a' && c <= 'z') continue;

                        // Create a new string by removing the character at index j
                        String child = cur.substring(0, j) + cur.substring(j + 1);

                        // If the new string has not been visited before, add it to the queue
                        if (!set.contains(child)) {
                            set.add(child);  // Mark as visited
                            q.add(child);    // Add to queue for further processing
                        }
                    }
                }
            }
        }
        return res;  // Return the list of valid expressions
    }
}
//Problem2
// Time Complexity :O(N+E) 
// Space Complexity :O(N)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    // tracking the node <old,new>
    Map<Node,Node> map;
    public Node clone(Node node)
    {
        if(map.containsKey(node))
        {
            return map.get(node);
        }
        Node newNode=new Node(node.val);// new node with the same value
        map.put(node,newNode);
        return newNode;
    }
    public Node cloneGraph(Node node) {
        if(node==null) return node;
        map=new HashMap<>();
        Node cloneNode=clone(node);
        Queue<Node> q=new LinkedList<>();
        q.add(node);// ading the first node, and start the bfs from the orig
        while(!q.isEmpty())
        {
            Node cur=q.poll();
            List<Node> list=cur.neighbors;
            for(Node neigh:list)
            {
                if(!map.containsKey(neigh))// if the neighbour is not cloned add in the queue
                {
                    q.add(neigh);
                }
                Node cloneN=clone(neigh);// clone the neighbour 
                map.get(cur).neighbors.add(cloneN); // add to it with the 
            }
        }
        // returnning
      return cloneNode;  
    }
}