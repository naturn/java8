package org.naturn.java8.pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @Author Naturn
 * 
 * @Date 2017年7月14日 - 上午9:58:35
 *
 * @Email juddersky@gmail.com
 *
 * @Version 0.0.1
 */

public class PatternDemo {

    public static void main(String[] args) {
        String pattern = "aabb";       
        String target = "他 他 我 我";
        PatternDemo demo = new PatternDemo();
       System.out.println(demo.pattern(pattern, target));
    }

    private boolean pattern(String pattern, String org) {
        return match(buildPattern(pattern), subString(pattern.length(), org));
    }

    private Node match(String s, Map<Integer, Node> mapper) {
        Node node = null;
        for (Entry<Integer, Node> entry : mapper.entrySet()) {
            if (entry.getValue().getName().equals(s)) {
                node = entry.getValue();
            }
        }
        return node;
    }

    private Map<Integer, Node> buildPattern(String pattern) {

        Map<Integer, Node> nodes = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            String s = pattern.substring(i, i + 1);
            Node node = match(s, nodes);

            if (null != node) {
                if (s.equals(pattern.substring(i-1,i))) {
                    node.addLength();
                } else {
                    node = new Node(s, i);
                    node.setLength(1);
                    nodes.put(i, node);
                }
            } else {
                node = new Node(s, i);
                node.setLength(1);
                nodes.put(i, node);
            }
        }
        return nodes;
    }

    private List<String[]> subString(int length, String org) {
        List<String[]> result = new ArrayList<>();
        String[] orgs = org.split(" ");
        if (length <= orgs.length) {
            for (int i = 0; i < orgs.length; i++) {
                if (i + length <= orgs.length) {
                    String[] temp = new String[4];
                    for (int j = 0; j < length; j++) {
                        temp[j] = orgs[i + j];
                    }
                    result.add(temp);
                } else {
                    break;
                }
            }
        }
        return result;
    }

    private boolean match(Map<Integer, Node> nodes, List<String[]> orgs) {

        boolean result = false;
        for (String[] org : orgs) {
            result = match(nodes, org);
        }
        return result;
    }

    private boolean match(Map<Integer, Node> nodes, String[] org) {

        for (Entry<Integer, Node> entry : nodes.entrySet()) {
            String start = org[entry.getKey()];
            int length = entry.getValue().getLength();
            for (int i = entry.getKey(); i < length; i++) {
                if (!start.equals(org[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    class Node {
        private Integer index;
        private String name;
        private int length;

        public Node(String name, int i) {
            this.name = name;
            this.index = i;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public void addLength() {
            this.length++;
        }
    }
}
