package test

import java.sql.Timestamp


object Database:

  final case class JsonValue[T](value: T)
  final case class JsonbValue[T](value: T)
  case class QuestionAndAnswer(ownerUserId: UserId,  question: Question, answer: Option[Answer] = None, relationId: Option[RelationId] = None, nodeId: Option[NodeId] = None, id: QAId = -1)
  case class User(email: Email,
                  tokens_left: Tokens,
                  tipFlags: TipFlags,
                  name: Option[UserName],
                  picture: Option[ProfilePicture],
                  loginType: LoginType,
                  activeWorkspaceId: Option[WorkspaceId] = None,
                  userStatus: UserStatus = UserStatus.Active,
                  passwordHash: Option[Hash] = None,
                  emailToken: Option[EmailConfirmationToken] = None,
                  emailTokenExpiresAt: Option[Timestamp] = None,
                  passwordResetToken: Option[PasswordResetToken] = None,
                  passwordResetTokenExpiresAt: Option[Timestamp] = None,
                  id: UserId = -1)
  case class UserSession(id: UserSessionId,
                         user_id: UserId,
                         created_at: Timestamp,
                         activeWorkspaceId: Option[WorkspaceId],
                         connectionId:Option[ConnectionId])
  case class Workspace(ownerUserId: UserId, topic:TopicName, spaceArea: JsonValue[SpaceArea] ,lang_id:LangId, id: WorkspaceId = -1)
  case class SpaceArea(zoom: Zoom , xPos: XPos, yPos: YPos)
  case class SystemParameter(name:SysParamName, parValue:SysParamValue)
  case class SystemParameters(acceptNewUsers:Boolean, allowLogin:Boolean, maintenanceMode: Boolean, aiDisabled: Boolean)
  case class Node(ownerUserId: UserId,
                  workspaceId: WorkspaceId,
                  title: NodeTitle,
                  content: NodeContent,
                  xPos: XPos,
                  yPos: YPos,
                  spaceArea: JsonValue[SpaceArea],
                  contentBig: Option[NodeContentBig] = None,
                  nodeColor: Option[Color] = None,
                  spaceAreaParentNodeId: Option[NodeId] = None,
                  id: NodeId = -1)
  case class Relation(ownerUserId: UserId,
                      workspaceId: WorkspaceId,
                      node1Id: NodeId,
                      node2Id: NodeId,
                      defaultQuestionAnswer: Option[RelationDefaultQuestionAnswer] = None,
                      //faq: Option[FaqJson] = None,
                      label: Option[RelationLabel] = None,
                      id: RelationId = 1)
  case class WorkspaceLanguage(iso_3166_1_alpha_3_id: LangId, native_name:NativeLangName, english_name: EnglishLangName)
