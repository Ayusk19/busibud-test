import java.util.*;

// array challenge

public class Main {

    public static String ArrayChallenge(String[] strArr) {
        Map<String, List<String>> parentToChildren = new HashMap<>();
        Map<String, String> childToParent = new HashMap<>();

        for (String pair : strArr) {
            pair = pair.replaceAll("[()]", ""); // Remove brackets
            String[] parts = pair.split(",");
            String child = parts[0];
            String parent = parts[1];

            // Check if child already has a parent
            if (childToParent.containsKey(child)) {
                return "false";
            }
            childToParent.put(child, parent);

            // Check if parent has more than 2 children
            parentToChildren.putIfAbsent(parent, new ArrayList<>());
            parentToChildren.get(parent).add(child);
            if (parentToChildren.get(parent).size() > 2) {
                return "false";
            }
        }

        // Find root node (node that is not a child of anyone)
        Set<String> allNodes = new HashSet<>();
        allNodes.addAll(childToParent.keySet());
        allNodes.addAll(parentToChildren.keySet());

        String root = null;
        int rootCount = 0;

        for (String node : allNodes) {
            if (!childToParent.containsKey(node)) {
                rootCount++;
                root = node;
            }
        }

        if (rootCount != 1) return "false";

        // Check for cycles using DFS
        Set<String> visited = new HashSet<>();
        if (!dfs(root, parentToChildren, visited)) {
            return "false";
        }

        // Make sure all nodes are connected (no separate trees)
        if (visited.size() != allNodes.size()) {
            return "false";
        }

        return "true";
    }

    // DFS helper function to check for cycles
    private static boolean dfs(String node, Map<String, List<String>> tree, Set<String> visited) {
        if (visited.contains(node)) return false; // cycle found

        visited.add(node);
        if (tree.containsKey(node)) {
            for (String child : tree.get(node)) {
                if (!dfs(child, tree, visited)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] input1 = {"(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"};
        System.out.println(ArrayChallenge(input1)); // Output: true

        String[] input2 = {"(1,2)", "(3,2)", "(2,12)", "(5,2)"};
        System.out.println(ArrayChallenge(input2)); // Output: false
    }
}
