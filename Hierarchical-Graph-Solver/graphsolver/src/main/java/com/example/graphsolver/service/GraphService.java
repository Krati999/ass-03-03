package com.example.graphsolver.service;

import com.example.graphsolver.model.Node;
import com.example.graphsolver.model.Relationship;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GraphService {

    private Map<String, Node> nodes = new HashMap<>();
    private List<Relationship> relationships = new ArrayList<>();

    // Add a node to the graph
    public void addNode(Node node) {
        nodes.put(node.getId(), node);
    }

    // Add a relationship between parent and child
    public void addRelationship(Relationship relationship) {
        Node parentNode = nodes.get(relationship.getParentId());
        Node childNode = nodes.get(relationship.getChildId());

        // Check if nodes exist
        if (parentNode == null || childNode == null) {
            throw new IllegalArgumentException("Parent or child node does not exist.");
        }

        // Initialize children list if not already done
        if (parentNode.getChildren() == null) {
            parentNode.setChildren(new ArrayList<>());
        }

        // Add child node to parent node's children list
        parentNode.getChildren().add(childNode);
    }

    // Find all nodes in the graph
    public List<Node> getAllNodes() {
        return new ArrayList<>(nodes.values());
    }

    // Find a path between two nodes (DFS or BFS could be implemented here)
    public List<String> findPath(String startNodeId, String endNodeId) {
        // Placeholder implementation (this could be a DFS or BFS)
        List<String> path = new ArrayList<>();
        path.add(startNodeId);
        path.add(endNodeId);
        return path;
    }

    // Calculate the depth of a node (distance from the root)
    public int calculateDepth(String nodeId) {
        Node node = nodes.get(nodeId);
        int depth = 0;

        while (node != null && node.getParentId() != null) {
            node = nodes.get(node.getParentId());
            depth++;
        }

        return depth;
    }

    // Find the common ancestor between two nodes
    public String findCommonAncestor(String nodeId1, String nodeId2) {
        Set<String> ancestors1 = new HashSet<>();
        Node node1 = nodes.get(nodeId1);
        Node node2 = nodes.get(nodeId2);

        // Collect ancestors of node1
        while (node1 != null && node1.getParentId() != null) {
            ancestors1.add(node1.getId());
            node1 = nodes.get(node1.getParentId());
        }

        // Find the first common ancestor
        while (node2 != null) {
            if (ancestors1.contains(node2.getId())) {
                return node2.getId();
            }
            node2 = nodes.get(node2.getParentId());
        }

        return null; // No common ancestor found
    }
}
