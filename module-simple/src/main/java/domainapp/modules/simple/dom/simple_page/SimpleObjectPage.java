package domainapp.modules.simple.dom.simple_page;

import javax.inject.Inject;

import org.apache.causeway.applib.ViewModel;
import org.apache.causeway.applib.annotation.DomainObject;
import org.apache.causeway.applib.annotation.DomainObjectLayout;
import org.apache.causeway.applib.annotation.Editing;
import org.apache.causeway.applib.annotation.Optionality;
import org.apache.causeway.applib.annotation.Property;

import domainapp.modules.simple.dom.so.SimpleObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@DomainObject
@DomainObjectLayout
public class SimpleObjectPage implements ViewModel {

    SimpleObjectPageService simpleObjectPageService;

    @Override
    public String viewModelMemento() {
        String memento = id.toString();
        log.info( "SimpleObjectPage view model memento created <{}>.", memento );
        return memento;
    }

    @Inject
    public SimpleObjectPage(final SimpleObjectPageService simpleObjectPageService, final String memento ) {

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
    @Property(editing = Editing.ENABLED, optionality = Optionality.OPTIONAL)
    private String name;

    @Getter
    @Property(editing = Editing.ENABLED, optionality = Optionality.OPTIONAL)
    private String notes;

}