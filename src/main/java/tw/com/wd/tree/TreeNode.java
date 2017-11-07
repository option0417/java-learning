package tw.com.wd.tree;

import java.util.HashMap;
import java.util.Map;

public class TreeNode {
	private String name;
	private Map<String, TreeNode> subNodeMap;
	private Map<String, TreeNode> superNodeMap;
	
	
	public TreeNode() {
		superNodeMap 	= new HashMap<String, TreeNode>();
		subNodeMap 		= new HashMap<String, TreeNode>();
	}
	
	public String getName() {
		return name;
	}
	
	public TreeNode setName(String name) {
		this.name = name;
		return this;
	}
	
	public Map<String, TreeNode> getSubNodeMap() {
		return subNodeMap;
	}
	
	public TreeNode addSubNode(TreeNode subNode) {
		this.subNodeMap.put(subNode.getName(), subNode);
		return this;
	}

	public Map<String, TreeNode> getSuperNodeMap() {
		return superNodeMap;
	}

	public TreeNode addSuperNode(TreeNode superNode) {
		this.superNodeMap.put(superNode.getName(), superNode);
		return this;
	}
}
