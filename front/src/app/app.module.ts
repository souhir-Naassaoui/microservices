import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CategoriesComponent } from './categories/categories.component';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MembersComponent } from './members/members.component';
import { EtudiantsComponent } from './etudiants/etudiants.component';
import { EnseignatsComponent } from './enseignats/enseignats.component';
import { RegisterComponent } from './register/register.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { ViewComponent } from './view/view.component';
import { ViewEtudiantComponent } from './view-etudiant/view-etudiant.component';
import { ViewPubComponent } from './view-pub/view-pub.component';
import { EventsComponent } from './events/events.component';
import { AddEventComponent } from './add-event/add-event.component';
import { EditEventComponent } from './edit-event/edit-event.component';

@NgModule({
  declarations: [
    AppComponent,
    CategoriesComponent,
    LoginComponent,
    MembersComponent,
    EtudiantsComponent,
    EnseignatsComponent,
    RegisterComponent,
    EditUserComponent,
    ViewComponent,
    ViewEtudiantComponent,
    ViewPubComponent,
    EventsComponent,
    AddEventComponent,
    EditEventComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
