import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Stack;
import java.util.Iterator;


// --== CS400 Fall 2022 File Header Information ==--
// Name: Michael Dinh
// Email: mtdinh@wisc.edu
// Team: ED
// TA: Sujitha
// Lecturer: Florian
// Notes to Grader: Used ideas from
// https://mariazacharia-k.medium.com/binary-search-tree-bst-iterator-e01b7b933594 for iterator
// Used ideas from https://www.programiz.com/dsa/deletion-from-a-red-black-tree for removal method
// Also had help from Jake Cochran, another student in CS400 for the removal method


/**
 * Red-Black Tree implementation with a Node inner class for representing the nodes of the tree.
 * Currently, this implements a Binary Search Tree that we will turn into a red black tree by
 * modifying the insert functionality. In this activity, we will start with implementing rotations
 * for the binary search tree insert algorithm. You can use this class' insert method to build a
 * regular binary search tree, and its toString method to display a level-order traversal of the
 * tree.
 */


public class AERedBlackTree<T extends Comparable<T>> implements IterableRBTADT<T> {

  /**
   * This class represents a node holding a single value within a binary tree the parent, left, and
   * right child references are always maintained.
   */
  protected static class Node<T> {
    public T data;
    public Node<T> parent; // null for root node
    public Node<T> leftChild;
    public Node<T> rightChild;

    public int blackHeight;

    // node constructor
    public Node(T data) {
      this.data = data;

      this.blackHeight = 0;
    }

    /**
     * @return true when this node has a parent and is the left child of that parent, otherwise
     *         return false
     */
    public boolean isLeftChild() {
      return parent != null && parent.leftChild == this;
    }

    /**
     * @return true when node has a parent, and it is a left child of said parent
     */
    public boolean isRightChild() {
      return parent != null && parent.rightChild == this;
    }

    /**
     * @return true if the node is left child of parent
     */
    public boolean isLeftChildOf(Node<T> parent) {
      return (this.isLeftChild() && (parent.leftChild.equals(this)));

    }

    /**
     * @return true if the node is right child of parent
     */
    public boolean isRightChildOf(Node<T> parent) {
      return (this.isRightChild() && (parent.rightChild.equals(this)));

    }


  }

  protected Node<T> root; // reference to root node of tree, null when empty
  protected int size = 0; // the number of values in the tree

  /**
   * Performs a naive insertion into a binary search tree: adding the input data value to a new node
   * in a leaf position within the tree. After this insertion, no attempt is made to restructure or
   * balance the tree. This tree will not hold null references, nor duplicate data values.
   *
   * @param data to be added into this binary search tree
   * @return true if the value was inserted, false if not
   * @throws NullPointerException     when the provided data argument is null
   * @throws IllegalArgumentException when the newNode and subtree contain equal data references
   */
  public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
    // null references cannot be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");

    Node<T> newNode = new Node<>(data);
    if (root == null) {
      root = newNode;
      size++;

      root.blackHeight = 1;
      return true;
    } // add first node to an empty tree
    else {
      boolean returnValue = insertHelper(newNode, root); // recursively insert into subtree
      if (returnValue)
        size++;
      else
        throw new IllegalArgumentException("This RedBlackTree already contains that value.");

      root.blackHeight = 1;
      return returnValue;
    }
  }

  /**
   * Recursive helper method to find the subtree with a null reference in the position that the
   * newNode should be inserted, and then extend this tree by the newNode in that position.
   *
   * @param newNode is the new node that is being added to this tree
   * @param subtree is the reference to a node within this tree which the newNode should be inserted
   *                as a descenedent beneath
   * @return true is the value was inserted in subtree, false if not
   */
  private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
    int compare = newNode.data.compareTo(subtree.data);
    // do not allow duplicate values to be stored within this tree
    if (compare == 0)
      return false;

      // store newNode within left subtree of subtree
    else if (compare < 0) {
      if (subtree.leftChild == null) { // left subtree empty, add here
        subtree.leftChild = newNode;
        newNode.parent = subtree;

        enforceRBTreePropertiesAfterInsert(newNode);

        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.leftChild);
    }

    // store newNode within the right subtree of subtree
    else {
      if (subtree.rightChild == null) { // right subtree empty, add here
        subtree.rightChild = newNode;
        newNode.parent = subtree;

        enforceRBTreePropertiesAfterInsert(newNode);

        return true;
        // otherwise continue recursive search for location to insert
      } else
        return insertHelper(newNode, subtree.rightChild);
    }
  }

  /**
   * Performs the rotation operation on the provided nodes within this tree. When the provided child
   * is a leftChild of the provided parent, this method will perform a right rotation. When the
   * provided child is a rightChild of the provided parent, this method will perform a left
   * rotation. When the provided nodes are not related in one of these ways, this method will throw
   * an IllegalArgumentException.
   *
   * @param child  is the node being rotated from child to parent position (between these two node
   *               arguments)
   * @param parent is the node being rotated from parent to child position (between these two node
   *               arguments)
   * @throws IllegalArgumentException when the provided child and parent node references are not
   *                                  initially (pre-rotation) related that way
   */
  private void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {


    // CASE 1: child is left child: right rotation
    if (child.isLeftChildOf(parent)) {

      // at the start of rotation, if the current parent is the root, set child to root
      if (parent == root) {
        root = child;
      }

      if (parent.parent != null) {
        // make child's parent to parent's parent
        child.parent = parent.parent;
      } else {
        child.parent = null;
      }

      if (parent.parent != null) {
        // set parent's parent's child to child
        if (parent.isLeftChild()) {
          parent.parent.leftChild = child;
        } else {
          parent.parent.rightChild = child;
        }
      }

      // store child's right child
      Node<T> childRChild = child.rightChild;


      // set parent to child's right child
      child.rightChild = parent;
      // set parent's parent to child
      parent.parent = child;

      // set child's right child to now parent's left child
      parent.leftChild = childRChild;

      // set child's right child's parent to parent
      if (childRChild != null) {
        childRChild.parent = parent;
      }

    }



    // CASE 2: child is right child: left rotation
    else if (child.isRightChildOf(parent)) {

      // at the start of rotation, if the current parent is the root, set child to root
      if (parent == root) {
        root = child;
      }

      // make child's parent to parent's parent
      if (parent.parent != null) {
        child.parent = parent.parent;
      } else {
        child.parent = null;
      }

      if (parent.parent != null) {
        // set parent's parent's child to child
        if (parent.isLeftChild()) {
          parent.parent.leftChild = child;
        } else {
          parent.parent.rightChild = child;
        }
      }

      // store child's left child
      Node<T> childLChild = child.leftChild;


      // set parent to child's left child
      child.leftChild = parent;
      // set parent's parent to child
      parent.parent = child;

      // set child's left child to now parent's right child
      parent.rightChild = childLChild;

      if (childLChild != null) {
        // set child's left child's parent to parent
        childLChild.parent = parent;
      }

    }

    // Case 3: Not related
    else
      throw new IllegalArgumentException("Nodes are not related");

  }

  /**
   * Get the size of the tree (its number of nodes).
   *
   * @return the number of nodes in the tree
   */
  public int size() {
    return size;
  }

  /**
   * Method to check if the tree is empty (does not contain any node).
   *
   * @return true of this.size() return 0, false if this.size() > 0
   */
  public boolean isEmpty() {
    return this.size() == 0;
  }

  /**
   * Checks whether the tree contains the value *data*.
   *
   * @param data the data value to test for
   * @return true if *data* is in the tree, false if it is not in the tree
   */
  public boolean contains(T data) {
    // null references will not be stored within this tree
    if (data == null)
      throw new NullPointerException("This RedBlackTree cannot store null references.");
    return this.containsHelper(data, root);
  }

  /**
   * Recursive helper method that recurses through the tree and looks for the value *data*.
   *
   * @param data    the data value to look for
   * @param subtree the subtree to search through
   * @return true of the value is in the subtree, false if not
   */
  private boolean containsHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return false;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return containsHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return containsHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return true;
      }
    }
  }

  /**
   * Note that this method may also be called recursively, in which case the input parameter may
   * reference a different red node in the tree that potentially has a red parent node. The job of
   * this enforceRBTreePropertiesAfterInsert method is to resolve any red-black tree property
   * violations that are introduced by inserting each new new node into a red-black tree.
   *
   * @param addedNode
   */
  protected void enforceRBTreePropertiesAfterInsert(Node<T> addedNode) {
    // addedNode is always red node (blackHeight = 0)
    // check if parent is a red node
    if ((addedNode.equals(root)) || (addedNode.parent.blackHeight == 1)
            || (addedNode.parent.parent == null)) {
      return;
    }

    // 2 red nodes in a row, need restructuring (therefore parent is not the root)
    if (addedNode.parent.blackHeight == 0) {

      // Case 2a: new node's parent's sibling is black or null
      if ((addedNode.parent.isLeftChild() && (addedNode.parent.parent.rightChild == null
              || addedNode.parent.parent.rightChild.blackHeight == 1))
              || (addedNode.parent.isRightChild() && (addedNode.parent.parent.leftChild == null
              || addedNode.parent.parent.leftChild.blackHeight == 1))) {

        // Case 2a1: new node is left child, parent is left child
        if (addedNode.isLeftChild() && addedNode.parent.isLeftChild()) {
          addedNode.parent.blackHeight = 1;
          addedNode.parent.parent.blackHeight = 0;

          rotate(addedNode.parent, addedNode.parent.parent);
          return;
        }

        // Case 2a2: new node is right child, parent is left child
        else if (addedNode.isRightChild() && addedNode.parent.isLeftChild()) {

          addedNode.blackHeight = 1;
          addedNode.parent.parent.blackHeight = 0;

          // rotate addedNode and parent
          rotate(addedNode, addedNode.parent);

          rotate(addedNode, addedNode.parent);
          return;
        }

        // Case 2a3: new node is right child, parent is right child
        else if (addedNode.isRightChild() && addedNode.parent.isRightChild()) {
          addedNode.parent.blackHeight = 1;
          addedNode.parent.parent.blackHeight = 0;

          rotate(addedNode.parent, addedNode.parent.parent);
          return;
        }

        // Case 2a4: new node is left child, parent is right child
        else if (addedNode.isLeftChild() && addedNode.parent.isRightChild()) {

          addedNode.blackHeight = 1;
          addedNode.parent.parent.blackHeight = 0;

          rotate(addedNode, addedNode.parent);
          rotate(addedNode, addedNode.parent);
          return;
        }
      }

      // Case 2b: new node's parent's sibling is red also

      // first check to see that there is a grandparent
      if (addedNode.parent.parent == null) {
        return;
      }

      // change parent and grandparent's color
      addedNode.parent.blackHeight = 1;
      addedNode.parent.parent.blackHeight = 0;

      // change siblings color
      if (addedNode.parent.isLeftChild()) { // parent is right child
        addedNode.parent.parent.rightChild.blackHeight = 1;
      } else { // parent is right child
        addedNode.parent.parent.leftChild.blackHeight = 1;
      }
      // recursively call enforce because the updated red node could be another violation
      this.enforceRBTreePropertiesAfterInsert(addedNode.parent.parent);
    }

  }


  /**
   * This method performs an inorder traversal of the tree. The string representations of each data
   * value within this tree are assembled into a comma separated string within brackets (similar to
   * many implementations of java.util.Collection, like java.util.ArrayList, LinkedList, etc). Note
   * that this RedBlackTree class implementation of toString generates an inorder traversal. The
   * toString of the Node class class above produces a level order traversal of the nodes / values
   * of the tree.
   *
   * @return string containing the ordered values of this tree (in-order traversal)
   */
  public String toInOrderString() {
    // generate a string of all values of the tree in (ordered) in-order
    // traversal sequence
    StringBuffer sb = new StringBuffer();
    sb.append("[ ");
    sb.append(toInOrderStringHelper("", this.root));
    if (this.root != null) {
      sb.setLength(sb.length() - 2);
    }
    sb.append(" ]");
    return sb.toString();
  }

  private String toInOrderStringHelper(String str, Node<T> node) {
    if (node == null) {
      return str;
    }
    str = toInOrderStringHelper(str, node.leftChild);
    str += (node.data.toString() + ", ");
    str = toInOrderStringHelper(str, node.rightChild);
    return str;
  }

  /**
   * This method performs a level order traversal of the tree rooted at the current node. The string
   * representations of each data value within this tree are assembled into a comma separated string
   * within brackets (similar to many implementations of java.util.Collection). Note that the Node's
   * implementation of toString generates a level order traversal. The toString of the RedBlackTree
   * class below produces an inorder traversal of the nodes / values of the tree. This method will
   * be helpful as a helper for the debugging and testing of your rotation implementation.
   *
   * @return string containing the values of this tree in level order
   */
  public String toLevelOrderString() {
    String output = "[ ";
    if (this.root != null) {
      LinkedList<Node<T>> q = new LinkedList<>();
      q.add(this.root);
      while (!q.isEmpty()) {
        Node<T> next = q.removeFirst();
        if (next.leftChild != null)
          q.add(next.leftChild);
        if (next.rightChild != null)
          q.add(next.rightChild);
        output += next.data.toString();
        if (!q.isEmpty())
          output += ", ";
      }
    }
    return output + " ]";
  }

  public String toString() {
    System.out.println(this.root.data);

    return "level order: " + this.toLevelOrderString() + "\nin order: " + this.toInOrderString();
  }


  public class Iterator implements java.util.Iterator<T> {

    Node<T> rootNode;
    // create a stack of the nodes that each contain a car to traverse through
    Stack<Node<T>> nodeStack = new Stack<Node<T>>();

    // constructor takes in the root node (actual root of the tree)
    public Iterator(Node<T> root) {

      rootNode = root;

      while (rootNode != null) {

        this.nodeStack.push(rootNode);
        // sets the root to the farthest left child
        // this node contains the smallest data
        rootNode = rootNode.leftChild;
      }
    }


    /**
     * This helper method for iterator traverses to the next bigger value, using a stack to keep
     * order correct
     *
     * @Return generic value T, our data
     */
    @Override
    public T next() {

      // Take node off top of stack, save as currentNode
      Node<T> currentNode = nodeStack.pop();

      // Store the data from node to return at end of method
      T nodeData = currentNode.data;

      // After gathering the data from the current node, move on to the next node in order (right
      // child)

      // This node could be null! (no right child)
      Node<T> nodeChild = currentNode.rightChild;



      while (nodeChild != null) {

        // add the node's right child to the stack
        this.nodeStack.push(nodeChild);

        // if has left child, will run through while loop again, add the child to stack, then see if
        // the child has a left child also
        nodeChild = nodeChild.leftChild;
      }

      // return the node's data value (in our case it is a Car)
      return nodeData;
    }

    /**
     * This helper method tells if there is another node to traverse through
     *
     * @return boolean of if there is another node to visit
     */
    @Override
    public boolean hasNext() {
      if (nodeStack.empty()) {
        return false;
      } else
        return true;
    }

  }

  /**
   * Helper method outputs an iterator for traversal of tree
   *
   * @return Iterator<T> to be used to iterate through this redblacktree, in 'in order' traversal
   */
  public java.util.Iterator<T> iterator() {

    return new Iterator(root);
  }

  /**
   * This method initiates the removal process for nodes in the RBT
   *
   * @param data of node to be removed from binary search tree
   * @return T that contains the data of the removed node
   */
  public T remove(T data) throws IllegalArgumentException {

    // check plate, if null or validate fails, throw IllegalArgumentException
    if ((data == null) || (data.equals(""))) {
      //throw new IllegalArgumentException("Cannot enter empty input");
      return null;
    }

    // node not in tree
    if (!this.contains(data)) {
      //throw new IllegalArgumentException (data + " is not stored in this tree");
      return null;
    }

    // node is in tree, find the node and call helper function
    else {

      Node<T> currentNodeChecked = root;

      // while loop terminates once currentNode's data matches the desired node's data to removed
      while (!currentNodeChecked.data.equals(data)) {
        if (currentNodeChecked.data.compareTo(data) > 0) {
          // current node value is greater than desired node, so node to remove is to left of
          // current
          currentNodeChecked = currentNodeChecked.leftChild;
        } else {
          // current node value is less than desired node, so node to remove is to right of current
          currentNodeChecked = currentNodeChecked.rightChild;
        }

      }

      T removedData = currentNodeChecked.data;

      // calls the helper method to actually remove the desired node found in this method
      removeHelper(currentNodeChecked);
      size--;
      return removedData;
    }
  }

  /**
   * This remove helper method chooses what sub-situation occurs when removal starts
   *
   * @param data of node to be removed from binary search tree
   * @return void
   */
  public void removeHelper(Node<T> removedNode) {

    // this int keeps track of what color the removed node was, to know how to fix tree
    int removedNodeColor;

    // with the remove method, the removed node is replaced with another node (smallest successor)
    Node<T> replacement;

    if (removedNode.rightChild == null || removedNode.leftChild == null) {
      replacement = situation1(removedNode);
      removedNodeColor = removedNode.blackHeight;
    }
    else {
      Node<T> removedSuccessor = removedNode.rightChild;

      while (removedSuccessor.leftChild != null) {

        removedSuccessor = removedSuccessor.leftChild;

      }

      removedNode.data = removedSuccessor.data;

      replacement = situation1(removedSuccessor);

      removedNodeColor = removedSuccessor.blackHeight;
    }

    if (removedNodeColor == 1) {

      enforceRedBlackPropertiesDelete(replacement);

      if (replacement.blackHeight == 2) {

        doubleBlackFix(replacement.parent, replacement, null);

      }
    }

    return;

  }

  /**
   * This helper method helps execute the remove method for one of many situations
   *
   * @param Node<T> the node that is being deleted
   * @return Node<T> the node that could cause further complications
   */
  public Node<T> situation1(Node<T> toDelete){
    if (toDelete.leftChild != null) {

      doubleBlackFix(toDelete.parent, toDelete, toDelete.leftChild);
      return toDelete.leftChild;

    }

    else if(toDelete.rightChild != null) {

      doubleBlackFix(toDelete.parent, toDelete, toDelete.rightChild);
      return toDelete.rightChild;

    }

    else {

      Node<T> potentialDoubleBlack;
      if (toDelete.blackHeight == 1) {
        potentialDoubleBlack = new Node<>(null);
        potentialDoubleBlack.blackHeight = 2;
      }

      else {
        potentialDoubleBlack = null;
      }

      doubleBlackFix(toDelete.parent, toDelete, potentialDoubleBlack);

      return potentialDoubleBlack;

    }
  }


  public void enforceRedBlackPropertiesDelete(Node<T> removed) {

    if (removed == root) {

      removed.blackHeight = 1;

      return;
    }

    Node<T> sibling = null;
    if (removed.isLeftChildOf(removed.parent)) {

      sibling = removed.parent.rightChild;
    }
    else if (removed.isRightChildOf(removed.parent)) {

      sibling = removed.parent.leftChild;
    }

    if (sibling.blackHeight == 0) {

      sibling.blackHeight = 1;

      removed.parent.blackHeight = 0;


      rotate(removed.parent, removed.parent.parent);

      if (removed.isLeftChildOf(removed.parent)) {
        sibling = removed.parent.rightChild;
      }
      else if (removed.isRightChildOf(removed.parent)) {
        sibling = removed.parent.leftChild;
      }

    }


    if ((sibling.leftChild.blackHeight == 1) && (sibling.rightChild.blackHeight == 1)) {
      sibling.blackHeight = 0;

      if (removed.parent.blackHeight == 0) {
        removed.parent.blackHeight = 1;
      }

      else {
        enforceRedBlackPropertiesDelete(removed.parent);
      }
    }

    else {
      boolean nodeIsLeftChild = removed == removed.parent.leftChild;


      if (nodeIsLeftChild && (sibling.rightChild.blackHeight == 1)) {

        sibling.leftChild.blackHeight = 1;
        sibling.blackHeight = 0;
        rotate(sibling,sibling.parent);
        sibling = removed.parent.rightChild;
      }
      else if (!nodeIsLeftChild && (sibling.leftChild.blackHeight == 1)) {
        sibling.rightChild.blackHeight = 1;
        sibling.blackHeight = 0;
        rotate(sibling, sibling.parent);
        sibling = removed.parent.leftChild;
      }


      sibling.blackHeight = removed.parent.blackHeight;
      removed.parent.blackHeight = 1;

      if (nodeIsLeftChild) {
        sibling.rightChild.blackHeight = 1;
        rotate(removed.parent, removed.parent.parent);
      }

      else {
        sibling.leftChild.blackHeight = 1;
        rotate(removed.parent, removed.parent.parent);
      }
    }

  }

  /**
   * This helper method helps execute the remove method if a double black node is formed after removal
   *
   * @param Node<T> parentNode of the double black node,
   * @param Node<T> replacedNode, the node that had a double black black height
   * @param Node<T> replacementNode the node that will replace the node causing problems
   *
   */
  public void doubleBlackFix(Node<T> parentNode, Node<T> replacedNode, Node<T> replacementNode) throws IllegalStateException{

    if (parentNode == null) {
      root = replacementNode;
    }
    else if (parentNode.leftChild == replacedNode) {
      parentNode.leftChild = replacementNode;
    }
    else if (parentNode.rightChild == replacedNode) {
      parentNode.rightChild = replacementNode;
    }
    else {
      throw new IllegalStateException("Node is not a child of its parent");
    }

    if (replacementNode != null) {
      replacementNode.parent = parentNode;
    }
  }



  public static void main(String[] args) {
    // unneeded
  }


  @Override
  public T search(T key) {
    return searchHelper(key, root);
  }

  public T searchHelper(T data, Node<T> subtree) {
    if (subtree == null) {
      // we are at a null child, value is not in tree
      return null;
    } else {
      int compare = data.compareTo(subtree.data);
      if (compare < 0) {
        // go left in the tree
        return searchHelper(data, subtree.leftChild);
      } else if (compare > 0) {
        // go right in the tree
        return searchHelper(data, subtree.rightChild);
      } else {
        // we found it :)
        return subtree.data;
      }
    }
  }



}