<g:if test="${flash?.message}">
  <div class="message">
	<g:message code="${flash.message}" />
	${flash.message = null}
  </div>
</g:if>
<g:if test="${flash?.error}">
  <div class="errors">
	<g:message code="${flash.error}" />
	${flash.error = null}
  </div>
</g:if>