package domainapp.modules.simple.dom.simple_page;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;

import org.apache.causeway.applib.services.factory.FactoryService;

import domainapp.modules.simple.SimpleModule;
import domainapp.modules.simple.dom.so.SimpleObject;
import domainapp.modules.simple.dom.so.SimpleObjectRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Named(SimpleModule.NAMESPACE + ".SimpleObjectPageService")
public class SimpleObjectPageService {

    @Inject FactoryService factoryService;
    @Inject SimpleObjectRepository simpleObjectRepository;

    public SimpleObjectPage buildSimpleObjectPage( final Long id ) {

        log.info( "Start building SimpleObjectPage with id <{}>.", id );
        SimpleObjectPage simpleObjectPage = factoryService
                .viewModel(new SimpleObjectPage(this, id.toString()));
        log.info( "Finish building SimpleObjectPage with id <{}>.", id );
        return simpleObjectPage;
    }

    public SimpleObjectData getSimpleObjectPageData( final Long id ) {

        log.info( "Start fetching SimpleObjectPage data for SimpleObject <{}>.", id );

        SimpleObject simpleObject = simpleObjectRepository.findById( id ).orElseThrow();

        SimpleObjectData data = new SimpleObjectData( simpleObject );
        log.info( "Finish fetching SimpleObjectPage data for SimpleObject <{}>.", id );
        return data;
    }
}