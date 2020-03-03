package com.harveyhaha.sample.model

import androidx.annotation.StringDef
import com.harveyhaha.sample.model.EventType.Companion.CheckRunEvent
import com.harveyhaha.sample.model.EventType.Companion.CheckSuiteEvent
import com.harveyhaha.sample.model.EventType.Companion.CommitCommentEvent
import com.harveyhaha.sample.model.EventType.Companion.ContentReferenceEvent
import com.harveyhaha.sample.model.EventType.Companion.CreateEvent
import com.harveyhaha.sample.model.EventType.Companion.DeleteEvent
import com.harveyhaha.sample.model.EventType.Companion.DeployKeyEvent
import com.harveyhaha.sample.model.EventType.Companion.DeploymentEvent
import com.harveyhaha.sample.model.EventType.Companion.DeploymentStatusEvent
import com.harveyhaha.sample.model.EventType.Companion.DownloadEvent
import com.harveyhaha.sample.model.EventType.Companion.FollowEvent
import com.harveyhaha.sample.model.EventType.Companion.ForkApplyEvent
import com.harveyhaha.sample.model.EventType.Companion.ForkEvent
import com.harveyhaha.sample.model.EventType.Companion.GistEvent
import com.harveyhaha.sample.model.EventType.Companion.GitHubAppAuthorizationEvent
import com.harveyhaha.sample.model.EventType.Companion.GollumEvent
import com.harveyhaha.sample.model.EventType.Companion.InstallationEvent
import com.harveyhaha.sample.model.EventType.Companion.InstallationRepositoriesEvent
import com.harveyhaha.sample.model.EventType.Companion.IssueCommentEvent
import com.harveyhaha.sample.model.EventType.Companion.IssuesEvent
import com.harveyhaha.sample.model.EventType.Companion.LabelEvent
import com.harveyhaha.sample.model.EventType.Companion.MarketplacePurchaseEvent
import com.harveyhaha.sample.model.EventType.Companion.MemberEvent
import com.harveyhaha.sample.model.EventType.Companion.MembershipEvent
import com.harveyhaha.sample.model.EventType.Companion.MetaEvent
import com.harveyhaha.sample.model.EventType.Companion.MilestoneEvent
import com.harveyhaha.sample.model.EventType.Companion.OrgBlockEvent
import com.harveyhaha.sample.model.EventType.Companion.OrganizationEvent
import com.harveyhaha.sample.model.EventType.Companion.PackageEvent
import com.harveyhaha.sample.model.EventType.Companion.PageBuildEvent
import com.harveyhaha.sample.model.EventType.Companion.ProjectCardEvent
import com.harveyhaha.sample.model.EventType.Companion.ProjectColumnEvent
import com.harveyhaha.sample.model.EventType.Companion.ProjectEvent
import com.harveyhaha.sample.model.EventType.Companion.PublicEvent
import com.harveyhaha.sample.model.EventType.Companion.PullRequestEvent
import com.harveyhaha.sample.model.EventType.Companion.PullRequestReviewCommentEvent
import com.harveyhaha.sample.model.EventType.Companion.PullRequestReviewEvent
import com.harveyhaha.sample.model.EventType.Companion.PushEvent
import com.harveyhaha.sample.model.EventType.Companion.ReleaseEvent
import com.harveyhaha.sample.model.EventType.Companion.RepositoryDispatchEvent
import com.harveyhaha.sample.model.EventType.Companion.RepositoryEvent
import com.harveyhaha.sample.model.EventType.Companion.RepositoryImportEvent
import com.harveyhaha.sample.model.EventType.Companion.RepositoryVulnerabilityAlertEvent
import com.harveyhaha.sample.model.EventType.Companion.SecurityAdvisoryEvent
import com.harveyhaha.sample.model.EventType.Companion.SponsorshipEvent
import com.harveyhaha.sample.model.EventType.Companion.StarEvent
import com.harveyhaha.sample.model.EventType.Companion.StatusEvent
import com.harveyhaha.sample.model.EventType.Companion.TeamAddEvent
import com.harveyhaha.sample.model.EventType.Companion.TeamEvent
import com.harveyhaha.sample.model.EventType.Companion.WatchEvent

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
