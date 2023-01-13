'use strict';

customElements.define('compodoc-menu', class extends HTMLElement {
    constructor() {
        super();
        this.isNormalMode = this.getAttribute('mode') === 'normal';
    }

    connectedCallback() {
        this.render(this.isNormalMode);
    }

    render(isNormalMode) {
        let tp = lithtml.html(`
        <nav>
            <ul class="list">
                <li class="title">
                    <a href="index.html" data-type="index-link">assignment5 documentation</a>
                </li>

                <li class="divider"></li>
                ${ isNormalMode ? `<div id="book-search-input" role="search"><input type="text" placeholder="Type to search"></div>` : '' }
                <li class="chapter">
                    <a data-type="chapter-link" href="index.html"><span class="icon ion-ios-home"></span>Getting started</a>
                    <ul class="links">
                        <li class="link">
                            <a href="overview.html" data-type="chapter-link">
                                <span class="icon ion-ios-keypad"></span>Overview
                            </a>
                        </li>
                        <li class="link">
                            <a href="index.html" data-type="chapter-link">
                                <span class="icon ion-ios-paper"></span>README
                            </a>
                        </li>
                                <li class="link">
                                    <a href="dependencies.html" data-type="chapter-link">
                                        <span class="icon ion-ios-list"></span>Dependencies
                                    </a>
                                </li>
                                <li class="link">
                                    <a href="properties.html" data-type="chapter-link">
                                        <span class="icon ion-ios-apps"></span>Properties
                                    </a>
                                </li>
                    </ul>
                </li>
                    <li class="chapter modules">
                        <a data-type="chapter-link" href="modules.html">
                            <div class="menu-toggler linked" data-toggle="collapse" ${ isNormalMode ?
                                'data-target="#modules-links"' : 'data-target="#xs-modules-links"' }>
                                <span class="icon ion-ios-archive"></span>
                                <span class="link-name">Modules</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                        </a>
                        <ul class="links collapse " ${ isNormalMode ? 'id="modules-links"' : 'id="xs-modules-links"' }>
                            <li class="link">
                                <a href="modules/AppModule.html" data-type="entity-link" >AppModule</a>
                                    <li class="chapter inner">
                                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ?
                                            'data-target="#components-links-module-AppModule-f99e384b4e00debbca1e54a4236f5231518c164a98be142be411d73da4617c641c8f04bd7da6801d707188f1054f05356550cad0ece4354e68f5581de5031520"' : 'data-target="#xs-components-links-module-AppModule-f99e384b4e00debbca1e54a4236f5231518c164a98be142be411d73da4617c641c8f04bd7da6801d707188f1054f05356550cad0ece4354e68f5581de5031520"' }>
                                            <span class="icon ion-md-cog"></span>
                                            <span>Components</span>
                                            <span class="icon ion-ios-arrow-down"></span>
                                        </div>
                                        <ul class="links collapse" ${ isNormalMode ? 'id="components-links-module-AppModule-f99e384b4e00debbca1e54a4236f5231518c164a98be142be411d73da4617c641c8f04bd7da6801d707188f1054f05356550cad0ece4354e68f5581de5031520"' :
                                            'id="xs-components-links-module-AppModule-f99e384b4e00debbca1e54a4236f5231518c164a98be142be411d73da4617c641c8f04bd7da6801d707188f1054f05356550cad0ece4354e68f5581de5031520"' }>
                                            <li class="link">
                                                <a href="components/AppComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >AppComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MspDetailsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MspDetailsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MspGridItemComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MspGridItemComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MspListComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MspListComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MspPartiesDetailsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MspPartiesDetailsComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MspPersonalDetailComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MspPersonalDetailComponent</a>
                                            </li>
                                            <li class="link">
                                                <a href="components/MspWebsiteDetailsComponent.html" data-type="entity-link" data-context="sub-entity" data-context-id="modules" >MspWebsiteDetailsComponent</a>
                                            </li>
                                        </ul>
                                    </li>
                            </li>
                </ul>
                </li>
                        <li class="chapter">
                            <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#injectables-links"' :
                                'data-target="#xs-injectables-links"' }>
                                <span class="icon ion-md-arrow-round-down"></span>
                                <span>Injectables</span>
                                <span class="icon ion-ios-arrow-down"></span>
                            </div>
                            <ul class="links collapse " ${ isNormalMode ? 'id="injectables-links"' : 'id="xs-injectables-links"' }>
                                <li class="link">
                                    <a href="injectables/MspService.html" data-type="entity-link" >MspService</a>
                                </li>
                            </ul>
                        </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#interfaces-links"' :
                            'data-target="#xs-interfaces-links"' }>
                            <span class="icon ion-md-information-circle-outline"></span>
                            <span>Interfaces</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? ' id="interfaces-links"' : 'id="xs-interfaces-links"' }>
                            <li class="link">
                                <a href="interfaces/MspEntry.html" data-type="entity-link" >MspEntry</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/MspPartyEntry.html" data-type="entity-link" >MspPartyEntry</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/PartyEntry.html" data-type="entity-link" >PartyEntry</a>
                            </li>
                            <li class="link">
                                <a href="interfaces/WebsiteEntity.html" data-type="entity-link" >WebsiteEntity</a>
                            </li>
                        </ul>
                    </li>
                    <li class="chapter">
                        <div class="simple menu-toggler" data-toggle="collapse" ${ isNormalMode ? 'data-target="#miscellaneous-links"'
                            : 'data-target="#xs-miscellaneous-links"' }>
                            <span class="icon ion-ios-cube"></span>
                            <span>Miscellaneous</span>
                            <span class="icon ion-ios-arrow-down"></span>
                        </div>
                        <ul class="links collapse " ${ isNormalMode ? 'id="miscellaneous-links"' : 'id="xs-miscellaneous-links"' }>
                            <li class="link">
                                <a href="miscellaneous/variables.html" data-type="entity-link">Variables</a>
                            </li>
                        </ul>
                    </li>
                        <li class="chapter">
                            <a data-type="chapter-link" href="routes.html"><span class="icon ion-ios-git-branch"></span>Routes</a>
                        </li>
                    <li class="chapter">
                        <a data-type="chapter-link" href="coverage.html"><span class="icon ion-ios-stats"></span>Documentation coverage</a>
                    </li>
                    <li class="divider"></li>
                    <li class="copyright">
                        Documentation generated using <a href="https://compodoc.app/" target="_blank">
                            <img data-src="images/compodoc-vectorise.png" class="img-responsive" data-type="compodoc-logo">
                        </a>
                    </li>
            </ul>
        </nav>
        `);
        this.innerHTML = tp.strings;
    }
});