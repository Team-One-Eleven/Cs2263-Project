/**
 * Listable Item interface
 *
 * All listable Items need a Listable Item Type, and Title/Name
 *
 * Item types can be found in the ListableType Enumeration.
 *
 *
 * @author  Traae
 * @version 1.0
 * @since 3/25/2021
 */

package Cs2263.Project.listable;

public interface ListableItem {

    String getTitle();
    ListableType getType();

}
