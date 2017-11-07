package tw.com.wd.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class TreeCreator {
	private static String DIVIDER 		= "/";
	private static String BRANCH_LIST_1 = "A01/B01/C01";
	private static String BRANCH_LIST_2 = "A02/B01/C02/D01";
	private static String BRANCH_LIST_3 = "A03/B02/C01";
	
	public static void main(String[] args) {
		TreeNode rootNode = new TreeNode().setName("ROOT");
		
		parserData(rootNode, BRANCH_LIST_1);
		
	}

	private static void parserData(TreeNode rootNode, String branchList) {
		int currIndex 				= 0;
		int dividerIndex 			= 0;
		int nodeIndex 				= 0;
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
		
		if ((dividerIndex = branchList.indexOf(DIVIDER, currIndex)) != -1) {
			String firstData = branchList.substring(currIndex, dividerIndex);			
			System.out.printf("FirstData: %s\n", firstData);
		}
		
		
		while( (dividerIndex = branchList.indexOf(DIVIDER, currIndex)) != -1) {
			String tmpData = branchList.substring(currIndex, dividerIndex);			
			System.out.printf("Data: %s\n", tmpData);
			TreeNode newNode = createTreeNode(tmpData)
					.addSuperNode(treeNodeList.get(nodeIndex));
			treeNodeList.get(nodeIndex).addSubNode(newNode);
			treeNodeList.add(newNode);
			nodeIndex++;
			currIndex = dividerIndex + 1;
		}
		String tmpData = branchList.substring(currIndex);
		System.out.printf("Data: %s\n", tmpData);
	}
	
	private String getBranchText(int startIndex, int endIndex, String sourceString) {
		return "";
	}
	
	private static TreeNode createTreeNode(String name) {
		return new TreeNode().setName(name);
	}
	
	private static void addToTree(TreeNode rootNode, TreeNode newNode) {
		Iterator<Entry<String, TreeNode>> rootIter = rootNode.getSubNodeMap().entrySet().iterator();
		
		while (rootIter.hasNext()) {
			Entry<String, TreeNode> entry = rootIter.next();			
			addToTree(entry.getValue(), newNode);
		}
		
		
		
		if (rootNode.getSubNodeMap().get(newNode.getName()) == null) {
			rootNode.getSubNodeMap().put(newNode.getName(), newNode);
		} else {
			
		}
	}
}
