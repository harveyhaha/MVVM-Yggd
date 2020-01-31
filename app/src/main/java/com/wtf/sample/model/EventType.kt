package com.wtf.sample.model

import androidx.annotation.StringDef

/**
 * Created by wtf on 1/31/20 10:39 PM.
 */
const val CheckRunEvent = "CheckRunEvent"
const val CheckSuiteEvent = "CheckSuiteEvent"
const val CommitCommentEvent = "CommitCommentEvent"
const val ContentReferenceEvent = "ContentReferenceEvent"
const val CreateEvent = "CreateEvent"
const val DeleteEvent = "DeleteEvent"
const val DeployKeyEvent = "DeployKeyEvent"
const val DeploymentEvent = "DeploymentEvent"
const val DeploymentStatusEvent = "DeploymentStatusEvent"
const val DownloadEvent = "DownloadEvent"
const val FollowEvent = "FollowEvent"
const val ForkEvent = "ForkEvent"
const val ForkApplyEvent = "ForkApplyEvent"
const val GitHubAppAuthorizationEvent = "GitHubAppAuthorizationEvent"
const val GistEvent = "GistEvent"
const val GollumEvent = "GollumEvent"
const val InstallationEvent = "InstallationEvent"
const val InstallationRepositoriesEvent = "InstallationRepositoriesEvent"
const val IssueCommentEvent = "IssueCommentEvent"
const val IssuesEvent = "IssuesEvent"
const val LabelEvent = "LabelEvent"
const val MarketplacePurchaseEvent = "MarketplacePurchaseEvent"
const val MemberEvent = "MemberEvent"
const val MembershipEvent = "MembershipEvent"
const val MetaEvent = "MetaEvent"
const val MilestoneEvent = "MilestoneEvent"
const val OrganizationEvent = "OrganizationEvent"
const val OrgBlockEvent = "OrgBlockEvent"
const val PackageEvent = "PackageEvent"
const val PageBuildEvent = "PageBuildEvent"
const val ProjectCardEvent = "ProjectCardEvent"
const val ProjectColumnEvent = "ProjectColumnEvent"
const val ProjectEvent = "ProjectEvent"
const val PublicEvent = "PublicEvent"
const val PullRequestEvent = "PullRequestEvent"
const val PullRequestReviewEvent = "PullRequestReviewEvent"
const val PullRequestReviewCommentEvent = "PullRequestReviewCommentEvent"
const val PushEvent = "PushEvent"
const val ReleaseEvent = "ReleaseEvent"
const val RepositoryDispatchEvent = "RepositoryDispatchEvent"
const val RepositoryEvent = "RepositoryEvent"
const val RepositoryImportEvent = "RepositoryImportEvent"
const val RepositoryVulnerabilityAlertEvent = "RepositoryVulnerabilityAlertEvent"
const val SecurityAdvisoryEvent = "SecurityAdvisoryEvent"
const val SponsorshipEvent = "SponsorshipEvent"
const val StarEvent = "StarEvent"
const val StatusEvent = "StatusEvent"
const val TeamEvent = "TeamEvent"
const val TeamAddEvent = "TeamAddEvent"
const val WatchEvent = "WatchEvent"

@StringDef(
    CheckRunEvent,
    CheckSuiteEvent,
    CommitCommentEvent,
    ContentReferenceEvent,
    CreateEvent,
    DeleteEvent,
    DeployKeyEvent,
    DeploymentEvent,
    DeploymentStatusEvent,
    DownloadEvent,
    FollowEvent,
    ForkEvent,
    ForkApplyEvent,
    GitHubAppAuthorizationEvent,
    GistEvent,
    GollumEvent,
    InstallationEvent,
    InstallationRepositoriesEvent,
    IssueCommentEvent,
    IssuesEvent,
    LabelEvent,
    MarketplacePurchaseEvent,
    MemberEvent,
    MembershipEvent,
    MetaEvent,
    MilestoneEvent,
    OrganizationEvent,
    OrgBlockEvent,
    PackageEvent,
    PageBuildEvent,
    ProjectCardEvent,
    ProjectColumnEvent,
    ProjectEvent,
    PublicEvent,
    PullRequestEvent,
    PullRequestReviewEvent,
    PullRequestReviewCommentEvent,
    PushEvent,
    ReleaseEvent,
    RepositoryDispatchEvent,
    RepositoryEvent,
    RepositoryImportEvent,
    RepositoryVulnerabilityAlertEvent,
    SecurityAdvisoryEvent,
    SponsorshipEvent,
    StarEvent,
    StatusEvent,
    TeamEvent,
    TeamAddEvent,
    WatchEvent
)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class EventType