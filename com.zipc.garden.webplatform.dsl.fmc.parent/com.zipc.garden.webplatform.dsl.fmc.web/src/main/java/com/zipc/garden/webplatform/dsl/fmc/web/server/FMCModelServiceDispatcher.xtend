package com.zipc.garden.webplatform.dsl.fmc.web.server

import com.google.inject.Inject
import com.google.inject.Singleton
import org.eclipse.xtext.util.internal.Log
import org.eclipse.xtext.web.server.IServiceContext
import org.eclipse.xtext.web.server.InvalidRequestException
import org.eclipse.xtext.web.server.XtextServiceDispatcher
import org.eclipse.xtext.web.server.generator.GeneratorService

@Log
@Singleton
class FMCModelServiceDispatcher extends XtextServiceDispatcher {

	@Inject GeneratorService generatorService;

	override protected createServiceDescriptor(String serviceType, IServiceContext context) {
		if(serviceType == "generate-all") {
			return getGeneratorAllService(context)
		}
		super.createServiceDescriptor(serviceType, context)
	}

	protected def getGeneratorAllService(IServiceContext context) throws InvalidRequestException {
		val document = getDocumentAccess(context)
		new ServiceDescriptor => [
			service = [
				try {
					generatorService.getResult(document, true)
				} catch(Throwable throwable) {
					handleError(throwable)
				}
			]
		]
	}
}
