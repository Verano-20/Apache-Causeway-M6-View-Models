package domainapp.modules.simple.dom.simple_page;

import domainapp.modules.simple.dom.so.SimpleObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.isis.applib.ViewModel;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.Property;

import javax.inject.Inject;
import javax.persistence.Transient;

import static org.apache.isis.applib.annotation.Editing.ENABLED;
import static org.apache.isis.applib.annotation.Optionality.OPTIONAL;

@Slf4j
@DomainObject
@DomainObjectLayout
public class SimpleObjectPage implements ViewModel {

    @Inject @Transient SimpleObjectPageService simpleObjectPageService;

    @Override
    public String viewModelMemento() {
        String memento = id.toString();
        log.info( "SimpleObjectPage view model memento created <{}>.", memento );
        return memento;
    }

    @Override
    public void viewModelInit( String memento ) {

        log.info( "Start init SimpleObjectPage view model from memento <{}>.", memento );

        Long mementoId = Long.valueOf( memento );
        id = mementoId;
        SimpleObjectData simpleObjectData = simpleObjectPageService.getSimpleObjectPageData( mementoId );

        SimpleObject simpleObject = simpleObjectData.getSimpleObject();
        if( simpleObject != null ) {
            name = simpleObject.getName();
            notes = simpleObject.getNotes();
        }

        log.info( "Finish init SimpleObjectPage view model from memento <{}>.", memento );
    }

    public String title() {

        return "Object: " + getName();
    }

    private Long id;

    /**
    Properties
     */

    @Getter
    @Property(editing = ENABLED, optionality = OPTIONAL)
    private String name;

    @Getter
    @Property(editing = ENABLED, optionality = OPTIONAL)
    private String notes;

}