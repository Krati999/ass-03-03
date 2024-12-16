package com.example.graphsolver.controller;

import com.example.graphsolver.model.Node;
import com.example.graphsolver.model.Relationship;
import com.example.graphsolver.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/graph")
public class GraphController {

    @Autowired
    private GraphService graphService;

    // Endpoint to add a node
    @PostMapping("/nodes")
    public String addNode(@RequestBody List<Node> node) {

            for(Node n : node)
            {
                graphService.addNode(n);
            }
        return "Node added successfully!";
    }

    // Endpoint to get all nodes
    @GetMapping("/nodes")
    public List<Node> getAllNodes() {
        return graphService.getAllNodes();
    }

    // Endpoint to add a relationship between nodes
    @PostMapping("/relationships")
    public String addRelationship(@RequestBody List<Relationship> relationship) {
        for(Relationship r: relationship){
        graphService.addRelationship(r);
        }
        return "Relationship added successfully!";
    }

    // Endpoint to find the path between two nodes
    @GetMapping("/paths")
    public List<String> findPath(@RequestParam String startNodeId, @RequestParam String endNodeId) {
        return graphService.findPath(startNodeId, endNodeId);
    }

    // Endpoint to calculate depth of a node
    @GetMapping("/nodes/{id}/depth")
    public int calculateDepth(@PathVariable String id) {
        return graphService.calculateDepth(id);
    }

    // Endpoint to find common ancestor of two nodes
    @GetMapping("/common-ancestor")
    public String findCommonAncestor(@RequestParam String nodeId1, @RequestParam String nodeId2) {
        return graphService.findCommonAncestor(nodeId1, nodeId2);
    }
}
