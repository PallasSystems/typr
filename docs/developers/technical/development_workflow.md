# Software Development Workflow

This section will outline how software will be developed, all feature development will take place in a dedicated branch instead of the main branch.

The dedicated branch will be generated from a feature/defect ticket (e.g. using the create branch functionality within Jira). The feature branch will be based on the ```main``` branch and must go through a pull request before the contents are merged into the ```main``` branch.

## Pull Requests

Once someone completes a feature it should not be immediately merged into ```main```, instead the appropriate feature branch should be pushed to the GitHub repository and a Pull Request to merge the feature into ```main``` branch should be opened.

Developers within the team should then review the pull request and provide feedback and changes should be made based on this. The feature branch should not be merged into ```main``` until at least 1 team member has approved it. All Pull Requests should be built and analysed by the appropriate Continious Integration (CI) with feedback on the changed code being supplied to the Pull Request. No feature branch should be merged into ```main``` unless it successfully builds with no test failures.

### Build Verification

The CI will run the following tools



### Code Review

Code review should focus on the following: