import java.util.*;
import java.io.*;

class Main {

  public static String ArrayChallenge(String[] strArr) {
    Map<String, Integer> childCount = new HashMap<>();
    Map<String, String> parentMap = new HashMap<>();
    Set<String> allNodes = new HashSet<>();

    for (String pair : strArr) {
      String[] parts = pair.replaceAll("[()]", "").split(",");
      String child = parts[0];
      String parent = parts[1];

      if (parentMap.containsKey(child)) return "false"; // Child has multiple parents
      parentMap.put(child, parent);

      childCount.put(parent, childCount.getOrDefault(parent, 0) + 1);
      if (childCount.get(parent) > 2) return "false"; // Parent has more than 2 children

      allNodes.add(child);
      allNodes.add(parent);
    }

    // Find root node(s)
    int rootCount = 0;
    for (String node : allNodes) {
      if (!parentMap.containsKey(node)) rootCount++;
    }

    return rootCount == 1 ? "true" : "false";
  }

  public static void main(String[] args) {
    String[] input1 = {"(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"};
    System.out.println(ArrayChallenge(input1)); // Output: true

    String[] input2 = {"(1,2)", "(3,2)", "(2,12)", "(5,2)"};
    System.out.println(ArrayChallenge(input2)); // Output: false
  }
}
