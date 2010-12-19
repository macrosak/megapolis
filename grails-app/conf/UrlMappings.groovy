class UrlMappings {

	static mappings = {
        "//$controller/$action?/$id?" {}
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "city")
		"500"(view:'/error')
	}
}
