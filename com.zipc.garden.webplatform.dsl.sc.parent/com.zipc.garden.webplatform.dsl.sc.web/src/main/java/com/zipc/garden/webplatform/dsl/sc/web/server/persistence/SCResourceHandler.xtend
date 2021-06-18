package com.zipc.garden.webplatform.dsl.sc.web.server.persistence

import java.io.IOException
import org.eclipse.xtext.web.server.IServiceContext
import org.eclipse.xtext.web.server.model.IXtextWebDocument
import org.eclipse.xtext.web.server.persistence.IServerResourceHandler

class SCResourceHandler implements IServerResourceHandler {

	override get(String resourceId, IServiceContext serviceContext) throws IOException {
		throw new IOException("This SCResourceHandler.")
	}

	override put(IXtextWebDocument document, IServiceContext serviceContext) throws IOException {
		throw new IOException("This SCResourceHandler.")
	}
}
