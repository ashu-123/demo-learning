package com.learning.demo.resource.constant;

import javax.swing.plaf.PanelUI;

/**
 * The class to hold resource constants for API layer.
 */
public class ResourceConstants {

    private ResourceConstants() { }

    private static final String SEPARATOR = "/";
    public static final String ROOT_PATH = SEPARATOR + "learning";
    public static final String CREATE_PERSON_PATH = SEPARATOR + "create-person";

    private static final String ID_PATH = SEPARATOR + "{id}";
    public static final String FIND_PERSON = SEPARATOR + "person" + ID_PATH;
}
