package test

import com.scalatsi.TSType

import scala.annotation.targetName


opaque type UserSessionId = String
object UserSessionId:
  def asString(name:UserSessionId):String =  name
  def apply(content: String): UserSessionId = content
  given TSType[UserSessionId] = tsTypeString

opaque type ConnectionId = String
object ConnectionId:
  def asString(name:ConnectionId):String =  name
  def apply(content: String): ConnectionId = content
  given TSType[ConnectionId] = tsTypeString

type TopicName = String

type SysParamName = String
type SysParamValue = String

type IdToken = String

opaque type TipFlagName = String
object TipFlagName:
  def asString(name:TipFlagName):String =  name
  def apply(content: String): TipFlagName = content
  given TSType[TipFlagName] = tsTypeString


opaque type TipFlags = String
object TipFlags:
  def apply(content: String): TipFlags = content
  given TSType[TipFlags] = tsTypeString

opaque type EmailConfirmationToken = String
object EmailConfirmationToken:
  def apply(content: String): EmailConfirmationToken = content
  given TSType[EmailConfirmationToken] = tsTypeString
  extension (c: EmailConfirmationToken)
    def asString: String = c

opaque type PasswordResetToken = String
object PasswordResetToken:
  def apply(content: String): PasswordResetToken = content
  given TSType[PasswordResetToken] = tsTypeString
  extension (c: PasswordResetToken)
    def asString: String = c

type Email = String

type Hash = String

opaque type QAId = Long
object QAId:
  def apply(id: Long): QAId = id
  def toLong(tokens: QAId): Long = tokens
  given TSType[QAId] = tsTypeLong

type Question = String

type Answer = String

type UserId = Long
object UserId:
  def apply(id: Long): UserId = id

type UserName = String

opaque type ProfilePicture = String
object ProfilePicture:
  def apply(value: String): ProfilePicture = value
  given TSType[ProfilePicture] = tsTypeString

enum LoginType {
  case Email, Google
}

enum UserStatus {
  case Active, WaitingEmailConfirmation, Blocked
}


//opaque type WorkspaceId = Long - fix for this is needed https://github.com/zio/zio-json/issues/444
type WorkspaceId = Long

opaque type RelationLabel = String
object RelationLabel:
  def apply(value: String): RelationLabel = value
  given TSType[RelationLabel] = tsTypeString

val tsTypeLong = TSType.get[Long]
opaque type Tokens = Long
object Tokens:
  def apply(id: Long): Tokens = id
  def toLong(tokens: Tokens): Long = tokens
  given TSType[Tokens] = tsTypeLong
  extension (c: Tokens)
    @targetName("minus")
    def -(tokens:Tokens): Tokens = c - tokens


object WorkspaceId:
  def apply(id: Long): WorkspaceId = id

opaque type Color = String
object Color:
  def apply(color: String): Color = color
  given TSType[Color] = tsTypeString



type NodeId = Long
object NodeId:
  def apply(id: Long): NodeId = id

val tsTypeString = TSType.get[String]
opaque type NodeContentBig = String
object NodeContentBig:
  def apply(content: String): NodeContentBig = content
  given TSType[NodeContentBig] = tsTypeString


type NodeContent = String
object NodeContent:
  def apply(content: String): NodeContent = content


type RelationDefaultQuestionAnswer = String



type NodeTitle = String
object NodeTitle:
  def apply(title: String): NodeTitle = title


opaque type RelationId = Long
object RelationId:
  def apply(id: Long): RelationId = id
  given TSType[RelationId] = tsTypeLong


val tsTypeFloat = TSType.get[Float]
opaque type XPos = Float
object XPos:
  def apply(pos: Float): XPos = pos
  given TSType[XPos] = tsTypeFloat


// opaque type YPos = Float - fix for this is needed https://github.com/zio/zio-json/issues/444
opaque type YPos = Float
object YPos:
  def apply(pos: Float): YPos = pos
  given TSType[YPos] = tsTypeFloat


type LangId = String
type NativeLangName = String
type EnglishLangName = String

type Zoom = Float

