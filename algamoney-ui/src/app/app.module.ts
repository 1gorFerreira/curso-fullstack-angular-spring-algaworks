import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { InputTextModule } from 'primeng/inputtext';
import { Button, ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { TooltipModule } from 'primeng/tooltip';
import { ReleasesSearchComponent } from './releases-search/releases-search.component';
import { NavbarComponent } from './navbar/navbar.component';
import { PersonsSearchComponent } from './persons-search/persons-search.component';

@NgModule({
  declarations: [
    AppComponent,
    ReleasesSearchComponent,
    NavbarComponent,
    PersonsSearchComponent
  ],
  imports: [
    TooltipModule,
    TableModule,
    ButtonModule,
    InputTextModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
