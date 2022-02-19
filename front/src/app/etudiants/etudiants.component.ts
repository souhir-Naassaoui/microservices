import { Router } from '@angular/router';
import { AppComponent } from './../app.component';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../authentication.service';
import { MembresService } from '../membres.service';

@Component({
  selector: 'app-etudiants',
  templateUrl: './etudiants.component.html',
  styleUrls: ['./etudiants.component.css']
})
export class EtudiantsComponent implements OnInit {

  constructor(private memberService:MembresService,private authService:AuthenticationService,
    public app:AppComponent,private router:Router) { }
  members:any;
  url:any;
  urlCV:any;
  ngOnInit(): void {
    this.memberService.getAllStudents().subscribe(data=>{
      this.members=data;
      this.url=this.url=this.memberService.memberHost+"/etudiants/photoEtudiant/";
      this.urlCV=this.memberService.memberHost+"/etudiants/cvEtudiant/";
    },err=>{
      console.log(err);
    })
  }
  onDelete(id:any){
    let v=confirm('Etes-vous sure de vouloir supprimer ');
    if(v)
    this.memberService.deleteMember(id).subscribe(data=>{
      this.onGetAllStudents();
    });
  }
 
  onGetAllStudents(){
    this.memberService.getAllStudents().subscribe(data=>{
      this.members=data;
    },err=>{
      console.log(err);
    })
  }
  onEdit(member:any){

  }
  isAuthenticated(){
    return this.authService.isAuthenticated();
  }
  isAdmin(){
    return this.authService.isAdmin();
  }
  onConsultEtudiant(id:any){
    this.router.navigateByUrl('/etudiants/'+id);
  }

}
