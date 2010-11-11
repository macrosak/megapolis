class UrlMappings {

	static mappings = {
        "//$controller/$action?/$id?" {}
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
