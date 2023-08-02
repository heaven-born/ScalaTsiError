package test

import com.scalatsi.TSType

import scala.annotation.targetName


type UserSessionId = String
type ConnectionId = String
type TopicName = String
type SysParamName = String
type SysParamValue = String
type IdToken = String
type TipFlagName = String
type TipFlags = String
type EmailConfirmationToken = String
type PasswordResetToken = String
type Email = String
type Hash = String
type QAId = Long
type Question = String
type Answer = String
type UserId = Long
type UserName = String
type ProfilePicture = String
enum LoginType {
  case Email, Google
}
enum UserStatus {
  case Active, WaitingEmailConfirmation, Blocked
}
type WorkspaceId = Long
type RelationLabel = String
type Tokens = Long
type Color = String
type NodeId = Long
type NodeContentBig = String
type NodeContent = String
type RelationDefaultQuestionAnswer = String
type NodeTitle = String
type RelationId = Long
type XPos = Float
type YPos = Float
type LangId = String
type NativeLangName = String
type EnglishLangName = String
type Zoom = Float

