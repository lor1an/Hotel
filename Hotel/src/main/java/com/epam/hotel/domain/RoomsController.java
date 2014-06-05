package com.epam.hotel.domain;

import javax.inject.Named;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

@Named(value = "roomsController")
@ViewScoped
public class RoomsController extends AbstractController<Room> {



    public RoomsController() {
        // Inform the Abstract parent controller of the concrete Rooms?cap_first Entity
        super(Room.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Orders entities that are
     * retrieved from Rooms?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Orders page
     */
    public String navigateOrdersCollection() {
      
        return "/orders/index";
    }

}
