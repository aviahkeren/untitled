public class RBTree
{
    private RBNode _root;
    private RBNode _nil ;

    //Constructors-

    /**
     * Default constructor.
     */
    public RBTree()
    {
        this._nil = new RBNode();
        this._nil.setColor(RBNode.Color.BLACK);//true
        this._root = this._nil;

        this._root.setRightSon(this._nil);
        this._root.setParent(this._nil);
        this._root.setLeftSon(this._nil);
    }

    /**
     * Constructor,get an RBNode as root.
     * @param node - The new root of this tree.
     */
    public RBTree(RBNode node)
    {
        this._nil= new RBNode();
        this._nil.setColor(RBNode.Color.BLACK);//true
        this._root=node;
        this._root.setParent(this._nil);
    }


    //Getters-

    /**
     * This method return the root,of this tree.
     * @return - The root of the current tree.
     */
    public RBNode getRoot()
    {
        return this._root;
    }//End of getRoot.


    /**
     * This method, get nil node,of current tree.
     * @return - The nil node, of current tree.
     */
    public RBNode getNill()
    {
        return this._nil;
    }//End of getRoot.



    //Setters-

    /**
     * This method,sets the root of this tree,to a new node.
     * @param node - The new root.
     */
    public void setRoot(RBNode node)
    {
        this._root = node;
    }//End of getRoot.


    //Methods -


    /**
     * RBInsert, insert an RBNode to the current(this) red-black tree.
     * If such key exist in the tree,do nothing.will not insert it.
     * @param node - The new node,to be insert.
     */
    public void RBInsert(RBNode node)
    {
        RBNode prev = this._nil,curr = this._root;
        //need to check if the new node not contain a key , that is already in the tree.
        if(this.search(node.getKey()) == this._nil)//If didn't find any node with the key store in node.(node to be insert).
        {
            while(curr != this._nil)
            {
                prev=curr;
                if( ( node.getKey().compareTo(curr.getKey()) )  < 0  )
                    curr=curr.getLeftSon();
                else
                    curr=curr.getRightSon();
            }

            node.setParent(prev);

            if(prev == this._nil)
                this._root=node;
            else
            {
                if( node.getKey().compareTo(prev.getKey())   < 0  )
                    prev.setLeftSon(node);
                else
                    prev.setRightSon(node);
            }
            node.setLeftSon(this._nil);
            node.setRightSon(this._nil);
            node.setColor(RBNode.Color.RED);
            RBInsertFixup(node);
        }
    }//End of RBInsert.


    /**
     * RBInsertFixup, fix the current(this) red black tree, to it exist all the features of a red black tree.
     * @param node - The...
     */
    private void RBInsertFixup(RBNode node)
    {
        RBNode uncle = this.getNill();
        while(node.getParent().getColor() == RBNode.Color.RED)
        {
            if(node.getParent() == node.getGrandparent().getLeftSon() )
            {
                uncle=node.getGrandparent().getRightSon();

                if(uncle.getColor()==RBNode.Color.RED)
                {
                    node.getParent().setColor(RBNode.Color.BLACK);
                    uncle.setColor(RBNode.Color.BLACK);

                    node = node.getGrandparent();
                    node.setColor(RBNode.Color.RED);
                }
                else
                {
                    if(node == node.getParent().getRightSon())
                    {
                        node = node.getParent();
                        LeftRotate(node);
                    }

                    node.getParent().setColor(RBNode.Color.BLACK);
                    node.getGrandparent().setColor(RBNode.Color.RED);

                    RightRotate(node.getGrandparent());
                }
            }
            else
            {
                uncle = node.getGrandparent().getLeftSon();

                if(uncle.getColor()==RBNode.Color.RED)
                {
                    node.getParent().setColor(RBNode.Color.BLACK);
                    uncle.setColor(RBNode.Color.BLACK);

                    node=node.getGrandparent();
                    node.setColor(RBNode.Color.RED);
                }
                else
                {
                    if(node == node.getParent().getLeftSon())
                    {
                        node = node.getParent();
                        RightRotate(node);
                    }

                    node.getParent().setColor(RBNode.Color.BLACK);
                    node.getGrandparent().setColor(RBNode.Color.RED);
                    LeftRotate(node.getGrandparent());
                }

            }
        }

        this._root.setColor(RBNode.Color.BLACK);//The root have to be black !.

    }//End of RNInsertFixup.



    /**
     * RBDelete, delete a node from the current(this) red black tree.
     * @param node - The node to be deleted.
     */
    public RBNode RBDelete(RBNode node)
    {
        RBNode nodeson=this._nil,temp=this._nil;

        if(node.getLeftSon() == this._nil || node.getRightSon() == this._nil )
            temp=node;
        else
            temp=TreeSuccessor(node);

        if(temp.getLeftSon() != this._nil)
            nodeson=temp.getLeftSon();
        else
            nodeson=temp.getRightSon();
        if(nodeson != this._nil)
            nodeson.setParent(temp.getParent());
        if(temp.getParent()==this._nil)
            this._root=nodeson;
        else if( temp == temp.getParent().getLeftSon() )
            temp.getParent().setLeftSon(nodeson);
        else
            temp.getParent().setRightSon(nodeson);
        if(temp != node) {
            String keyToUpdate = temp.getKey();
            node.setKey(keyToUpdate);
        }
        if(temp.getColor()==RBNode.Color.BLACK)//if the color is black.//true
            RBDeleteFixup(nodeson);
        return nodeson;
    }//End of RBDelete.





    /**
     * rbDeleteFixup, fix the current(this) red black tree, for it exist all the features of a red black tree.
     * @param node - The node
     */
    public void RBDeleteFixup(RBNode node)
    {
        RBNode bro = this._nil;

        while( node != this._root  && node.getColor()==RBNode.Color.BLACK )
        {
            if(node == node.getParent().getLeftSon())
            {
                bro=node.getParent().getRightSon();
                if(bro.getColor() == RBNode.Color.RED)
                {
                    bro.setColor(RBNode.Color.BLACK);
                    node.getParent().setColor(RBNode.Color.RED);
                    LeftRotate(node.getParent());
                }

                if(bro.getLeftSon().getColor()== RBNode.Color.BLACK && bro.getRightSon().getColor()== RBNode.Color.BLACK)
                {
                    bro.setColor(RBNode.Color.RED);
                    node=node.getParent();
                }
                else
                {
                    if(bro.getRightSon().getColor()==RBNode.Color.BLACK)
                    {
                        bro.getLeftSon().setColor(RBNode.Color.BLACK);
                        bro.setColor(RBNode.Color.RED);
                        RightRotate(bro);
                        bro=node.getParent().getRightSon();
                    }

                    bro.setColor(node.getParent().getColor());
                    node.getParent().setColor(RBNode.Color.BLACK);
                    bro.getRightSon().setColor(RBNode.Color.BLACK);
                    LeftRotate(node.getParent());
                    node=this._root;
                }
            }
            else
            {
                bro=node.getParent().getLeftSon();

                if(bro.getColor()==RBNode.Color.RED)
                {
                    bro.setColor(RBNode.Color.BLACK);
                    node.getParent().setColor(RBNode.Color.RED);
                    RightRotate(node.getParent());

                }

                if( bro.getRightSon().getColor() == RBNode.Color.BLACK && bro.getLeftSon().getColor() == RBNode.Color.BLACK )
                {
                    bro.setColor(RBNode.Color.RED);
                    node=node.getParent();
                }
                else
                {
                    if(bro.getLeftSon().getColor()== RBNode.Color.BLACK)
                    {
                        bro.getRightSon().setColor(RBNode.Color.BLACK);
                        bro.setColor(RBNode.Color.RED);
                        LeftRotate(bro);
                        bro=node.getParent().getLeftSon();
                    }

                    bro.setColor(node.getParent().getColor());
                    node.getParent().setColor(RBNode.Color.BLACK);
                    bro.getLeftSon().setColor(RBNode.Color.BLACK);
                    RightRotate(node.getParent());
                    node=this._root;
                }
            }

        }//While.

        node.setColor(RBNode.Color.BLACK);//root have to be black.

    }//End of RBDeleteFixup.



    //****************************************************************************************
    //service methods-

    /**
     * TreeSuccessor, find the successor of a node. meaning the next key value.
     * @param node - The node,which the method search its successor.
     * @return - The node that contain the Successor.or null,if not exist.
     */
    public RBNode TreeSuccessor(RBNode node)
    {
        RBNode successor=this._nil;

        if(node.getRightSon() != this._nil)
            return TreeMin(node.getRightSon());

        successor=node.getParent();

        while(successor!=this._nil && node==successor.getRightSon())
        {
            node=successor;
            successor=successor.getParent();
        }

        return successor;
    }//End of TreeSuccessor.


    /**
     * TreeMin, return the node with the smallest key in the sub-tree, which node(parameter) is its root.
     * @param node - The root of the sub tree, searched for minimum key value.
     * @return - The Node with the smallest key, in the sub tree,whose node is its root.
     */
    private RBNode TreeMin(RBNode node)
    {

        while(node.getLeftSon() != this._nil)
            node=node.getLeftSon();

        return node;
    }//End of TreeMin.



    /**
     * This method search a node in a red black tree,that contain a specific key.
     * @param key - The key,been searched.
     * @return - The node which contain the given key,
     * 			 _nil if not exist.
     */
    public RBNode search(String key)
    {
        RBNode curr = this._root;

        if(curr == null)
            return this._nil;

        while ( curr != this._nil  )
        {
            if( key.equals(curr.getKey()))
                return curr;
            else if (  key.compareTo(curr.getKey()) > 0  )
                curr=curr.getRightSon();
            else
                curr=curr.getLeftSon();
        }

        return curr;
    }//End of search.




    /**
     * This method, rotate the given node and its son around the axe.
     * @param node - The node, which the rotate is between it and its son.
     */
    private void LeftRotate(RBNode node)
    {
        RBNode son=node.getRightSon();
        node.setRightSon(son.getLeftSon());

        if(son.getLeftSon() != this._nil)
            son.getLeftSon().setParent(node);

        son.setParent(node.getParent());

        if( node.getParent() == this._nil )
            this._root=son;
        else if( node == node.getParent().getLeftSon())
            node.getParent().setLeftSon(son);
        else
            node.getParent().setRightSon(son);

        son.setLeftSon(node);
        node.setParent(son);

    }//End of LeftRotate.


    /**
     * This method, rotate the given node and its son around the axe.
     * @param node - The node, which the rotate is between it and its son.
     */
    private void RightRotate(RBNode node)
    {

        RBNode son=node.getLeftSon();
        node.setLeftSon(son.getRightSon());

        if(son.getRightSon() != this._nil)
            son.getRightSon().setParent(node);

        son.setParent(node.getParent());

        if( node.getParent() == this._nil )
            this._root=son;
        else if( node == node.getParent().getLeftSon())
            node.getParent().setLeftSon(son);
        else
            node.getParent().setRightSon(son);

        son.setRightSon(node);
        node.setParent(son);
    }//End of RightRotate.


    /**
     * This method,prints the current tree, sorted.
     * @param node - The root of the tree been printed.
     */
    public void printInOrder(RBNode node)
    {
        if(node != this._nil)
        {
            this.printInOrder(node.getLeftSon());
            System.out.println(node.getKey());
            this.printInOrder(node.getRightSon());
        }
    }//End of printInOrder.


    /**
     * This method, copy all the data from source node to dest node.
     * It makes sure that all data which points to source node, will point to dest node.
     * It uses during the deletion of a node in red-black tree.
     * @param dest - The new node, that replace the old one.
     * @param source - The old node, been erase.
     */
    public void moveNodeData(RBNode dest,RBNode source)//dest to source.
    {
        dest.setKey(source.getKey());
    }//End of moveNodeData.


}
