import { HttpEventType, HttpResponse } from '@angular/common/http';
import { MembresService } from './membres.service';
import { Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'front';

  currentUsername!:any;
  currentUser:any;
  type:any;
  public url:any;
  editPhoto:any;
  selectedFiles:any;
  public progress:number=0;
  public currentUploadedFiles:any;
  constructor(private authService:AuthenticationService, private router:Router,private memberService:MembresService){
    
  }
  ngOnInit(): void {
    this.authService.loadToken();
    this.currentUsername=this.authService.currentUsername;
    this.authService.getUser().subscribe(data=>{
      this.currentUser=JSON.stringify(data);
      this.currentUser=JSON.parse(this.currentUser);
      this.type=this.currentUser.type;
      if(this.type=="ens"){
        this.url=this.memberService.memberHost+"/enseignants/photoEnseignant/"+this.currentUser.id;
      }else{
        this.url=this.memberService.memberHost+"/etudiants/photoEtudiant/"+this.currentUser.id;
      }
      //console.log(this.currentUser);
    //console.log(this.currentUsername);
  })}

  isAdmin(){
    return this.authService.isAdmin();
  }

  isUser(){
    return this.authService.isUser();
  }
  isAuthenticated(){
    return this.authService.isAuthenticated();
  }
  logOut(){
    this.authService.logout();
    this.router.navigateByUrl("/");
  }
  onEdit(){
    this.authService.getUser().subscribe(data=>{
      this.currentUser=JSON.stringify(data);
      this.currentUser=JSON.parse(this.currentUser);
      this.type=this.currentUser.type;
      if(this.type=="ens"){
        this.url=this.memberService.memberHost+"/enseignants/photoEnseignant/"+this.currentUser.id;
      }else{
        this.url="this.memberService.memberHost+'/etudiants/photoEtudiant/"+this.currentUser.id;
      }
      console.log(this.currentUser);
      this.router.navigateByUrl("/editMember");
    })
    
  }
  onEditPhoto(){
    //console.log(this.currentUser)
    this.editPhoto=true;
  }

  onSelectedFile(event:any){
    this.selectedFiles=event.target.files;
  }
  uploadPhoto(){
    this.progress=0;
    this.currentUploadedFiles=this.selectedFiles.item(0);
    console.log(this.currentUploadedFiles);
    this.memberService.uploadPhotoProduct(this.currentUploadedFiles,this.currentUser.id,this.currentUser.type).subscribe(event=>{
      if(event.type===HttpEventType.UploadProgress){
        this.progress=Math.round(100*event.loaded/event.total!);
      }else if(event instanceof HttpResponse){
        this.router.navigateByUrl('/');
      }
    },err=>{
      alert("Probleme de chargement "+JSON.parse(err.error).message);
    });
    this.selectedFiles=undefined;
  }
  
}
