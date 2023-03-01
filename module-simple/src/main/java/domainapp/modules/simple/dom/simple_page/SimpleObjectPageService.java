package domainapp.modules.simple.dom.simple_page;

import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.isis.applib.services.factory.FactoryService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Slf4j
@Service
public class SimpleObjectPageService {

    @Inject FactoryService factoryService;
    @Inject SimpleObjectRepository simpleObjectRepository;

    public SimpleObjectPage buildSimpleObjectPage( final Long id ) {

        log.info( "Start building SimpleObjectPage with id <{}>.", id );
        SimpleObjectPage SimpleObjectPage = factoryService.viewModel( SimpleObjectPage.class, id.toString() );
        log.info( "Finish building SimpleObjectPage with id <{}>.", id );
        return SimpleObjectPage;
    }

    public SimpleObjectData getSimpleObjectPageData( final Long id ) {

        log.info( "Start fetching SimpleObjectPage data for SimpleObject <{}>.", id );

        SimpleObject simpleObject = simpleObjectRepository.findById( id ).orElseThrow();

        SimpleObjectData data = new SimpleObjectData( simpleObject );
        log.info( "Finish fetching SimpleObjectPage data for SimpleObject <{}>.", id );
        return data;
    }
}