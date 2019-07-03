
public class RBNode
{
    private String _key;
    private RBNode _parent,_rightSon,_leftSon;
    private Color _color;


    //constructors-
    /**
     * Default constructor.
     */
    public RBNode()
    {
        this._key=null;
        this._parent = this._leftSon = this._rightSon = null;
        //this._color=false;
        this._color = Color.RED;

    }

    /**
     * Constructor, get a key,as parameter.
     * The key will be the prime key of the Red-Black Tree structure.
     * @param key - The key of a node,in a Red-Black Tree.
     */
    public RBNode(String key)
    {
        this._key = key;
        this._parent=this._leftSon=this._rightSon=null;
        //this._color=false;
        this._color=Color.RED;
    }



    //Getters-

    /**
     * This method return the key of the node.
     * @return - The key of the node.
     */
    public String getKey()
    {
        return this._key;
    }//End of getKey.

    /**
     * This method return the parent node of the current node.
     * @return - The parent node of the current node.
     */
    public RBNode getParent()
    {
        return this._parent;
    }//End of getParent.

    /**
     * This method return the Grandparent node of the current node.
     * @return - The Grandparent node of the current node.
     */
    public RBNode getGrandparent()
    {
        if(this._parent != null && this._parent.getParent() != null)
            return this._parent.getParent();
        return null;
    }//End of getGrandparent.

    /**
     * This method return the right son node of the current node.
     * @return - The right son node of the current node.
     */
    public RBNode getRightSon()
    {
        return this._rightSon;
    }//End of getRightSon.

    /**
     * This method return the left son node of the current node.
     * @return - The left son node of the current node.
     */
    public RBNode getLeftSon()
    {
        return this._leftSon;
    }//End of getLeftSon.

    /**
     * This method return the color of the current node.
     * @return - The color of the current node.
     */
    public Color getColor()
    {
        return this._color;
    }//End of getColor.



    //Setters -

    /**
     * This method, uses to set the key,of current node.
     * The method change the key,only if the new one, contain only digits.
     * @param key - The new key of the current node.
     */
    public void setKey(String key)
    {
        this._key=key;
    }//End of setKey.

    /**
     * This method sets the parent of the current node.
     * @param parent - The new parent.
     */
    public void setParent(RBNode parent)
    {
        this._parent = parent;
    }//End of setParent.

    /**
     * This method sets the right son of the current node.
     * @param rightSon - The new right son.
     */
    public void setRightSon(RBNode rightSon)
    {
        this._rightSon = rightSon;
    }//End of setRightSon.

    /**
     * This method sets the left son of the current node.
     * @param leftSon - The new left son.
     */
    public void setLeftSon(RBNode leftSon)
    {
        this._leftSon = leftSon;
    }//End of setLeftSon.


    /**
     * This method sets the color of the current node.
     * @param color - The new parent.
     */
    public void setColor(Color color)
    {
        this._color=color;
    }//End of setColor.



    public enum Color {BLACK,RED}//function as attribute, that tell us if a node is red or black.

}
