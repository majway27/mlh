package com.majway.mlh.rugrat;

import java.util.ArrayList;

public class Child {

    // external system child id ex. 2765
    private int id;
    // external system child name
    private String name;
    // external system child type/e_id
    private String type;

    public Child() {
        super();
    }

    public Child(int id, String name, String type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }**/

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Child other = (Child) obj;
        if (id != other.id)
            return false;
        return true;
    }

    /**@Override
    public String toString() {
        return "Child [id=" + id + ", name=" + name + ", type="
                + type + "]";
    }**/

    /**public static ArrayList<Child> getChildren() {
        ArrayList<Child> children = new ArrayList<Child>();
        children.add(new Child(1, "Test Child1", "Infant"));
        children.add(new Child(2, "Test Child2", "Child"));
        children.add(new Child(3, "Test Child3", "Infant"));
        children.add(new Child(4, "Test Child4", "Child"));
        return children;
    }**/

}
