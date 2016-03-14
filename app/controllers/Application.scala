package controllers

import javax.inject.Inject

import daos.Tables
import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc.{Action, Controller}
import services.SchemaService

class Application @Inject() (val messagesApi: MessagesApi) extends Controller with I18nSupport{


}
