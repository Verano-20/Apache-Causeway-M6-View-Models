package domainapp.webapp.application.services.homepage;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.Transient;

import domainapp.modules.simple.dom.simple_page.SimpleObjectPage;
import domainapp.modules.simple.dom.simple_page.SimpleObjectPageService;
import org.apache.isis.applib.annotation.DomainObject;
import org.apache.isis.applib.annotation.DomainObjectLayout;
import org.apache.isis.applib.annotation.HomePage;
import org.apache.isis.applib.annotation.Nature;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjects;

@DomainObject(
        nature = Nature.VIEW_MODEL,
        logicalTypeName = "simple.HomePageViewModel"
        )
@HomePage
@DomainObjectLayout()
public class HomePageViewModel {

    public String title() {
        return getObjects().size() + " objects";
    }

    public List<SimpleObject> getObjects() {
        return simpleObjects.listAll();
    }

    public List<SimpleObjectPage> getObjectViewModels() {
        return simpleObjects.listAll()
                .stream()
                .map( SimpleObject::getId )
                .map( simpleObjectPageService::buildSimpleObjectPage )
                .collect( Collectors.toList() );
    }

    @Inject @Transient SimpleObjectPageService simpleObjectPageService;

    @Inject SimpleObjects simpleObjects;
}
