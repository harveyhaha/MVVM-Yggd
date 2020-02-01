package com.wtf.sample.model

import androidx.annotation.StringDef
import com.wtf.sample.model.EventType.Companion.CheckRunEvent
import com.wtf.sample.model.EventType.Companion.CheckSuiteEvent
import com.wtf.sample.model.EventType.Companion.CommitCommentEvent
import com.wtf.sample.model.EventType.Companion.ContentReferenceEvent
import com.wtf.sample.model.EventType.Companion.CreateEvent
import com.wtf.sample.model.EventType.Companion.DeleteEvent
import com.wtf.sample.model.EventType.Companion.DeployKeyEvent
import com.wtf.sample.model.EventType.Companion.DeploymentEvent
import com.wtf.sample.model.EventType.Companion.DeploymentStatusEvent
import com.wtf.sample.model.EventType.Companion.DownloadEvent
import com.wtf.sample.model.EventType.Companion.FollowEvent
import com.wtf.sample.model.EventType.Companion.ForkApplyEvent
import com.wtf.sample.model.EventType.Companion.ForkEvent
import com.wtf.sample.model.EventType.Companion.GistEvent
import com.wtf.sample.model.EventType.Companion.GitHubAppAuthorizationEvent
import com.wtf.sample.model.EventType.Companion.GollumEvent
import com.wtf.sample.model.EventType.Companion.InstallationEvent
import com.wtf.sample.model.EventType.Companion.InstallationRepositoriesEvent
import com.wtf.sample.model.EventType.Companion.IssueCommentEvent
import com.wtf.sample.model.EventType.Companion.IssuesEvent
import com.wtf.sample.model.EventType.Companion.LabelEvent
import com.wtf.sample.model.EventType.Companion.MarketplacePurchaseEvent
import com.wtf.sample.model.EventType.Companion.MemberEvent
import com.wtf.sample.model.EventType.Companion.MembershipEvent
import com.wtf.sample.model.EventType.Companion.MetaEvent
import com.wtf.sample.model.EventType.Companion.MilestoneEvent
import com.wtf.sample.model.EventType.Companion.OrgBlockEvent
import com.wtf.sample.model.EventType.Companion.OrganizationEvent
import com.wtf.sample.model.EventType.Companion.PackageEvent
import com.wtf.sample.model.EventType.Companion.PageBuildEvent
import com.wtf.sample.model.EventType.Companion.ProjectCardEvent
import com.wtf.sample.model.EventType.Companion.ProjectColumnEvent
import com.wtf.sample.model.EventType.Companion.ProjectEvent
import com.wtf.sample.model.EventType.Companion.PublicEvent
import com.wtf.sample.model.EventType.Companion.PullRequestEvent
import com.wtf.sample.model.EventType.Companion.PullRequestReviewCommentEvent
import com.wtf.sample.model.EventType.Companion.PullRequestReviewEvent
import com.wtf.sample.model.EventType.Companion.PushEvent
import com.wtf.sample.model.EventType.Companion.ReleaseEvent
import com.wtf.sample.model.EventType.Companion.RepositoryDispatchEvent
import com.wtf.sample.model.EventType.Companion.RepositoryEvent
import com.wtf.sample.model.EventType.Companion.RepositoryImportEvent
import com.wtf.sample.model.EventType.Companion.RepositoryVulnerabilityAlertEvent
import com.wtf.sample.model.EventType.Companion.SecurityAdvisoryEvent
import com.wtf.sample.model.EventType.Companion.SponsorshipEvent
import com.wtf.sample.model.EventType.Companion.StarEvent
import com.wtf.sample.model.EventType.Companion.StatusEvent
import com.wtf.sample.model.EventType.Companion.TeamAddEvent
import com.wtf.sample.model.EventType.Companion.TeamEvent
import com.wtf.sample.model.EventType.Companion.WatchEvent

/**
 * Created by wtf on 1/31/20 10:39 PM.
 */
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
@Retention(AnnotationRetention.SOURCE)
annotation class EventType{
    companion object{
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
    }
}
