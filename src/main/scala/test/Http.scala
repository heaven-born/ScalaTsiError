package test

import com.scalatsi.*
import com.scalatsi.TypescriptType.TSBoolean
import com.scalatsi.dsl.*
import test.Database.WorkspaceLanguage

import java.io.StringReader
import java.util.Properties
import scala.annotation.targetName

object Http {

  enum BackendError :
    case SessionExpiredOrDoesNotExist()
    case AIError()
    case AIDisabled()
    case UnknownServerError()
    case AIInappropriateContent()
    case NewUsersAreNotAllowed()
    case LoginsAreForbidden()
    case UnderMaintenance()
    case AccountIsBlocked()
    case NoTokensLeft()


  case class AWSWebSocketHeaders(
                                  cookie: String,
                                  host: String,
                                  origin: String,
                                  secWebSocketKey: String
                                )

  case class AWSIntegrationWebSocketRequest(
                                             connectionId: ConnectionId,
                                             domain: String,
                                             stage: String,
                                             headers: Option[AWSWebSocketHeaders],
                                             body: Option[WSRequest]
                                           )

  case class AWSIntegrationWebSocketResponse(
                                              statusCode: String,
                                              headers: AWSWebSocketHeaders
                                            )

  enum BackendAuthRequests :
    case GoogleLoginRequest(clientId: String, credential: String)
    case EmailLoginRequest(email: String, hash: String)
    case LogoutRequest()
    case ResendConfirmationEmailRequest(userId: UserId)
    case RegisterNewUserRequest(email:Email, passwordHash: Hash )
    case ForgotPasswordRequest(email:Email)
    case PasswordResetRequest(passwordResetToken: PasswordResetToken, newPasswordHash: Hash, oldPasswordHash: Hash)
    case SessionVerificationRequest()

  enum BackendAuthResponses :
    case EmailLoginResponse(status: LoginStatus, userId: Option[UserId])
    case GoogleLoginResponse()
    case ForgotPasswordResponse(status:ResetPasswordStatus)
    case LogoutResponse()
    case PasswordResetResponse(status:ResetPasswordConfirmStatus)
    case RegisterNewUserResponse(status:RegistrationStatus, userId: UserId)
    case ResendConfirmationEmailResponse(status:ResendConfirmationEmailStatus)

  enum BackendRequests :
    case DeleteNodeRequest(nodeId: NodeId)
    case DeleteRelationRequest(relationId: RelationId)
    case DeleteWorkspaceRequest(wsId: WorkspaceId)
    case GetActiveWorkspaceDataRequest(spaceAreaParentNodeId: Option[NodeId])
    case GetTermHintsRequest(relatedNode: Option[NodeId], spaceAreaParentNodeId: Option[NodeId])
    case UpdateNodePositionRequest(nodeId: NodeId, xPos: XPos, yPos: YPos)
    case ListWorkspacesRequest(excludeActiveWorkspace: Boolean)
    case ListWorkspaceLanguagesRequest()
    case AddNewRelationRequest(node1Id: NodeId, node2Id: NodeId)
    case AddNewNodeRequest(title: NodeTitle, xPos: XPos, yPos: YPos, relatedNode: Option[RelationParam], content: Option[NodeContent], spaceAreaParentNodeId: Option[NodeId])
    case AddWorkspaceRequest(topic: TopicName, langId: LangId)
    case SetActiveWorkspaceRequest(wsId: WorkspaceId)
    case UpdateRelationLabelRequest(relationId: RelationId, label: RelationLabel)
    case UpdateWorkspaceTransformRequest(zoom: Zoom, xPos: XPos, yPos: YPos, spaceAreaParentNodeId: Option[NodeId])
    case UpdateWorkspaceTopicRequest(wsId: WorkspaceId, topic: TopicName, langId: LangId)
    case UpdateNodeColorRequest(nodeId: NodeId, color: Color)
    case UpdateNodeContentRequest(nodeId: NodeId, title: NodeTitle, content: NodeContent)
    case SetTipFlagRequest(flagName: TipFlagName)
    case GetTipFlagsRequest()
    case ActiveWorkspaceInformationRequest()
    case SelectionTextInfoRequest(nodeId: NodeId, selectionText: String)
    case BigTermDescriptionRequest(nodeId: NodeId)
    case FaqListRequest(nodeId: NodeId)
    case NodeQuestionRequest(nodeId: NodeId, questionId: QAId)
    case RelationQuestionRequest(relationId: RelationId, node1Id: NodeId, node2Id: NodeId, questionId: Option[QAId])
    case RelationFaqListRequest(relationId: RelationId, node1Id: NodeId, node2Id: NodeId)

  case class WSRequest(id: String, payload: BackendRequests)
  case class WSResponse(id: String, payload: Option[BackendResponses], error: Option[BackendError])

  enum BackendResponses :
    case SetActiveWorkspaceResponse()
    case DeleteNodeResponse()
    case DeleteRelationResponse()
    case DeleteWorkspaceResponse()
    case AddNewNodeResponse(node: NodeResp, relationId: Option[RelationId] = None, tokensLeft: Tokens)
    case AddNewRelationResponse(relation: RelationResp)
    case AddWorkspaceResponse(wsId: WorkspaceId)
    case GetActiveWorkspaceDataResponse(nodes: List[NodeResp], relations: List[RelationResp], workspace: WorkspaceResp, tokensLeft: Tokens, user: UserParam)
    case GetTermHintsResponse(terms: List[String], tokensLeft: Tokens)
    case ListWorkspacesResponse(workspaces: List[WorkspaceTopicResp])
    case ListWorkspaceLanguagesResponse(languages: List[WorkspaceLanguage])
    case UpdateNodePositionResponse()
    case UpdateNodeColorResponse()
    case UpdateNodeContentResponse()
    case UpdateWorkspaceTransformResponse()
    case UpdateWorkspaceTopicResponse()
    case UpdateRelationLabelResponse()
    case SetTipFlagResponse()
    case GetTipFlagsResponse(flags: TipFlags)
    case SessionVerificationResponse()
    case ActiveWorkspaceInformationResponse(activeWorkspaceIsAvailable: Boolean)
    case SelectionTextInfoResponse(text: String, tokensLeft: Tokens)
    case BigTermDescriptionResponse(text: NodeContentBig, tokensLeft: Tokens)
    case FaqListResponse(questions: List[QuestionAndAnswerParam], tokensLeft: Tokens)
    case NodeQuestionResponse(response: String, tokensLeft: Tokens)
    case RelationQuestionResponse(response: String, tokensLeft: Tokens)
    case RelationFaqListResponse(questions: List[QuestionAndAnswerParam], tokensLeft: Tokens)


  enum LoginStatus :
    case Success
    case InvalidLoginOrPassword
    case EmailNotConfirmed

  enum ResetPasswordStatus :
    case PasswordResetEmailSent
    case UserHasUnconfirmedEmail(userId: UserId) // when user didn't complete registration
    case UserDoesNotExist

  enum ResetPasswordConfirmStatus :
    case InvalidOldPassword
    case TokenExpired
    case UserWithTokenNotFound
    case Success

  enum RegistrationStatus :
    case ActiveUserWithEmailAlreadyExists(loginType: LoginType)
    case UnconfirmedUserWithEmailAlreadyExists
    case Success

  enum ResendConfirmationEmailStatus :
    case UserIsAlreadyActive
    case UserDoesNotExist
    case Done

  case class UserParam(userName: Option[UserName], userPictureUrl: Option[ProfilePicture], email: String)

  case class RelationParam(nodeId: NodeId, createRelation: Boolean)

  case class QuestionAndAnswerParam(id: QAId, question: Question, answer: Option[Answer])
  
  case class WorkspaceResp(topic: TopicName, lang_id: LangId, zoom: Zoom, xPos: XPos, yPos: YPos, id: WorkspaceId)

  case class WorkspaceTopicResp(id: WorkspaceId, topic: TopicName)

  case class NodeResp(
                       id: NodeId,
                       title: NodeTitle,
                       content: NodeContent,
                       xPos: XPos,
                       yPos: YPos,
                       spaceAreaNodeId: Option[NodeId],
                       color: Option[Color],
                       bigContentIsReady: Boolean,
                       faqIsIsReady: Boolean,
                       emptySpaceArea: Boolean
                     )

  case class RelationResp(
                           workspaceId: WorkspaceId,
                           node1Id: NodeId,
                           node2Id: NodeId,
                           defaultQuestionAnswerIsReady: Boolean,
                           faqIsIsReady: Boolean,
                           label: Option[RelationLabel],
                           id: RelationId = RelationId(-1)
                         )




}
