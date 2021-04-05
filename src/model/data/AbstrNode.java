//dependencies
package model.data;

import model.scaling.UrgencyAccuracy;

import javax.swing.tree.TreeNode;
import java.util.stream.Stream;
//responsibility of Huang


//edited by Beckett

//Last checked 22/10 03:44 AM

//The abstract class for creating any type of node in the tree.
public abstract class AbstrNode implements TreeNode { //an abstract node contains...
    //These cannot be static otherwise the tree doesn't construct itself properly.
    protected String name; //a name
    protected String desc; //a description
    protected UrgencyAccuracy defaultScale; //a scale of accuracy
    protected Project parent; //potentially a parent

    //the enum represents a list of constants. in our case a node can be a project or a task.
    public enum AnyNode {
        PROJ("Project"), //not currently used, but do not delete please!
        TASK("Task");
        String name;
        AnyNode(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }

    /**
     * Constructor for initalising a node.
     *
     * @param name the name of the node
     * @param desc the description
     * @param parentProject a parent, if any
     * @param urgencyAccuracy the default scale of accuracy for this project
     */
    //Construct fields for a node. A node is public because other stuff access it frequently.
    public AbstrNode(String name, String desc, Project parentProject, UrgencyAccuracy urgencyAccuracy) {
        this.name = name.trim(); //the name
        this.desc = desc.trim(); //the description
        this.defaultScale = urgencyAccuracy; //the scale of accuracy chosen
        this.parent = parentProject; //the parent
        if(parent != null) {
            parent.add(this); //if there is a parent, add it, otherwise leave null
        }
    }

    /**
     * Retrieve node name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieve node description
     * @return description
     */
    public String getDesc() {
        return desc;
    }


    /**
     * Retrieve parent if one exists, else retrieve root
     * @return parent
     */
    public Project getParent() {
        return parent;
    }

    /**
     * Retrieve the scale of accuracy
     * @return scale
     */
    public UrgencyAccuracy scale() {
        return defaultScale;
    }

    /**
     * Get Stream of urgency info for the node
     * @return Stream
     */
    public abstract Stream<UrgencyAdd> getAccuracy();

    /**
     * Delete a child from the collection for this chosen node.
     * @param selectedItem is what's being deleted
     */
    public abstract boolean deleteChild(TreeNode selectedItem);

    //Tree things below

    /**
     * Returns true if children allowed.
     * @return bool for true/false statement.
     */
    @Override
    public boolean getAllowsChildren() {
        return true;
    }
    //this should always return true because it's always possible to add sub tasks
}
