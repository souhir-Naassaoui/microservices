import { EditEventComponent } from './edit-event/edit-event.component';
import { EventsComponent } from './events/events.component';
import { ViewPubComponent } from './view-pub/view-pub.component';
import { ViewEtudiantComponent } from './view-etudiant/view-etudiant.component';
import { ViewComponent } from './view/view.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { EnseignatsComponent } from './enseignats/enseignats.component';
import { EtudiantsComponent } from './etudiants/etudiants.component';
import { RegisterComponent } from './register/register.component';
import { MembersComponent } from './members/members.component';
import { LoginComponent } from './login/login.component';
import { CategoriesComponent } from './categories/categories.component';

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddEventComponent } from './add-event/add-event.component';

const routes: Routes = [
  {path:"publications",component:CategoriesComponent},
  {path:"login",component:LoginComponent},
  {path:'register',component:RegisterComponent},
  {path:'etudiants',component:EtudiantsComponent},
  {path:'enseignants',component:EnseignatsComponent},
  {path:'editMember',component:EditUserComponent},
  {path:'enseignants/:id',component:ViewComponent},
  {path:'etudiants/:id',component:ViewEtudiantComponent},
  {path:'publications/:id',component:ViewPubComponent},
  {path:'events',component:EventsComponent},
  {path:'addEvent',component:AddEventComponent},
  {path:'editEvent/:id',component:EditEventComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
