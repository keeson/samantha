package org.grouplens.samantha.modeler.space;

import com.google.inject.ImplementedBy;

import java.io.Serializable;

/**
 * Every method needs to be thread-safe.
 */
@ImplementedBy(SynchronizedIndexSpace.class)
public interface IndexSpace extends Serializable {
    void setSpaceName(String spaceName);
    void requestKeyMap(String name);
    boolean hasKeyMap(String name);
    int setKey(String name, Object key);
    boolean containsKey(String name, Object key);
    int getIndexForKey(String name, Object key);
    Object getKeyForIndex(String name, int index);
    int getKeyMapSize(String name);
}