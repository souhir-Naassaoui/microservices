import { Router } from '@angular/router';
import { AppComponent } from './../app.component';
import { AuthenticationService } from './../authentication.service';
import { Component, OnInit } from '@angular/core';
import { MembresService } from '../membres.service';

@Component({
  selector: 'app-enseignats',
  templateUrl: './enseignats.component.html',
  styleUrls: ['./enseignats.component.css']
})
export class EnseignatsComponent implements OnInit {

  
  constructor(public memberService:MembresService,private authService:AuthenticationService,
    public app:AppComponent, private router:Router) { }
  members:any;
  url:any;
  urlCV:any;
    
  ngOnInit(): void {
    this.memberService.getAllTeachers().subscribe(data=>{
      this.members=data;
      this.url=this.memberService.memberHost+"/enseignants/photoEnseignant/";
      this.urlCV=this.memberService.memberHost+"/enseignants/cvEnseignant/";
    },err=>{
      console.log(err);
    })
    
  }
  onDelete(id:any){
    let v=confirm('Etes-vous sure de vouloir supprimer ');
    if(v)
    this.memberService.deleteMember(id).subscribe(data=>{
      this.onGetAllTeachers();
    });
  }
 
  onGetAllTeachers(){
    this.memberService.getAllTeachers().subscribe(data=>{
      this.members=data;
    },err=>{
      console.log(err);
    })
  }
  onEdit(member:any){

  }
  addRole(username:string){
    this.memberService.addRole(username);
  }
  isAuthenticated(){
    return this.authService.isAuthenticated();
  }
  isAdmin(){
    return this.authService.isAdmin();
  }
  onConsultEnseignant(id:any){
    this.router.navigateByUrl('/enseignants/'+id);
  }


}
