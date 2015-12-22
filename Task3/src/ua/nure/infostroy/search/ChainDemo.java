package ua.nure.infostroy.search;

public class ChainDemo {
	public static Chain initChain() {
		Chain rootChain = new FileChain();
		Chain extensionChain = new ExtensionChain();
		Chain sizeChain = new SizeChain();
		Chain dateChain = new DateChain();
		sizeChain.setNextChain(dateChain);
		extensionChain.setNextChain(sizeChain);
		rootChain.setNextChain(extensionChain);
		return rootChain;
	}
	public static void main(String[] args) {
		String root ="C:\\Users\\Eugne\\Downloads";
		initChain().search(root);
	}
}
