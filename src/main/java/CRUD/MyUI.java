package CRUD;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import CRUD.com.User;
import CRUD.session.SessionRun;
import com.vaadin.data.HasValue;
import com.vaadin.ui.*;
import java.util.List;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
    private String thoseName = "";
    private int age = 0;
    private TextArea label = showUser();
    private CheckBox checkBox = createCheckAdmin();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);
        layout.addComponent(createButton_add());
        layout.addComponent(createButton_remove());
        layout.addComponent(creadField());
        layout.addComponent(checkBox);
        layout.addComponent(creadField_age());
        layout.addComponent(createButton_show_byName());
        layout.addComponent(createButton_show_byID());
        layout.addComponent(createButton_showAll());
        layout.addComponent(label);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    private Button createButton_add()
    {
        Button button = button = new Button("BUTTON_ADD");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                new SessionRun().addUser(new User(1, checkBox.getValue(), thoseName, (age <= 0) ? 1 : age));
            }
        });
        return   button;
    }

    private Button createButton_remove()
    {
        Button button = button = new Button("BUTTON_DELETE");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                new SessionRun().deleteUser(age);
            }
        });
        return button;
    }

    private Button createButton_show_byID()
    {
        Button button = button = new Button("ShowById");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                label.setValue(new SessionRun().getOneById(age).toString());
            }
        });
        return button;
    }

    private Button createButton_show_byName()
    {
        Button button = button = new Button("ShowByName");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String s ="";
                for(User user : new SessionRun().getOneByName(thoseName))
                {
                    s += user.toString() + "\n";
                }
                label.setValue(s);
            }
        });
        return button;
    }

    private Button createButton_showAll()
    {
        Button button = button = new Button("ShowAll");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                showAll(new SessionRun().getAll());
            }
        });
        return button;
    }

    private TextField creadField()
    {
        TextField field = new TextField("Enter name / Search by name");
        field.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> event) {
                thoseName = event.getValue();
            }
        });
        /*field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                thoseName = textChangeEvent.getText();
            }
        });*/
        return field;
    }

    private CheckBox createCheckAdmin() {
        checkBox = new CheckBox();
        checkBox.setValue(false);
        checkBox.setDescription("iAdmin (only for a new User)");
        return checkBox;
    }

    private TextField creadField_age()
    {
        TextField field = new TextField("Enter age / Search | Delete by id");
        field.addValueChangeListener(new HasValue.ValueChangeListener<String>() {
            @Override
            public void valueChange(HasValue.ValueChangeEvent<String> event) {
                age = Integer.parseInt(event.getValue());
            }
        });
        /*field.addTextChangeListener(new FieldEvents.TextChangeListener() {
            @Override
            public void textChange(FieldEvents.TextChangeEvent textChangeEvent) {
                age = Integer.parseInt(textChangeEvent.getText());
            }
        });*/
        return field;
    }

    private TextArea showUser()
    {
        label = new TextArea();
        label.setEnabled(false);
        label.setWidth("100%");
        label.setHeight("200");
        return label;
    }



    private void showAll(List<User> users)
    {
        label.setValue("");
        String s = "";
        for(User user : users)
        {
            s += user.toString() + "\n";
        }
        label.setValue(s);
    }
}