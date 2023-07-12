// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.lang.*;
import java.util.*;

class HelloWorld {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addVertex(1, new int[]{1, 2});
        graph.addVertex(2, new int[]{1, 3});
        graph.addVertex(3, new int[]{1, 4});
        graph.addVertex(4, new int[]{3, 5});
        graph.addVertex(5, new int[]{2, 4});
        graph.DFS(1);
        graph.BFS(1);
    }
}

class Node {
    public int data;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return data == node.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}

class Graph {
    public Map<Node, List<Node>> adjList = new HashMap();

    public void addNode(int data) {
        adjList.putIfAbsent(new Node(data), new ArrayList());
    }

    public void addVertex(int node, int[] vertices) {
        Node nodeKey = new Node(node);
        if (adjList.containsKey(nodeKey)) {
            List<Node> lstNode = adjList.get(nodeKey);
            for (int data : vertices) {
                lstNode.add(new Node(data));
            }
            adjList.put(nodeKey, lstNode);
        }
    }

    public void DFS(int start) {
        Stack<Node> traversalStack = new Stack();
        Set<Integer> visited = new HashSet();
        traversalStack.push(new Node(start));
        while (!traversalStack.isEmpty()) {
            Node node = traversalStack.pop();
            if (!visited.contains(node.data)) {
                System.out.print(node.data + " ");
                visited.add(node.data);
                List<Node> lst = adjList.get(node);
                for (Node dataNode : lst) {
                    traversalStack.push(dataNode);
                }
            }
        }
    }

    public void BFS(int start) {
        Queue<Node> queue = new LinkedList();
        Set<Integer> visited = new HashSet<>();
        queue.add(new Node(start));
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (!visited.contains(node.data)) {
                visited.add(node.data);
                System.out.print(node.data + " ");
                List<Node> lstNode = adjList.get(node);
                for (Node nodeData : lstNode) {
                    queue.add(nodeData);
                }
            }
        }
    }
}