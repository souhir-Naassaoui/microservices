import { Etudiant } from './../model/Etudiant.model';
import { Router } from '@angular/router';
import { MembresService } from './../membres.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AppComponent } from './../app.component';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  submitted:boolean=false;
  productFormGroup!:FormGroup;


  currentUser?:any;
  type!:any;
  etd=false;
  ens=false;
  public enseignants:any;
  public encadrant:any;
  constructor(private app:AppComponent,private fb:FormBuilder,private memberService:MembresService,
    private router:Router) {
    this.currentUser=this.app.currentUser;
   
   }



  ngOnInit(): void {
      this.type=this.currentUser.type;
      this.memberService.getAllTeachers().subscribe(data=>{
        this.enseignants=data;
      })
      if(this.type=="ens"){
        this.ens=true;
        this.getEnseignant();
      }else{
        this.etd=true;
        this.getEtudiant();
      }
    
  }
  
  getEtudiant(){
    this.productFormGroup=this.fb.group({
      id:[this.currentUser.id,Validators.required],
      username:[this.currentUser.username,Validators.required],
      password:[this.currentUser.password,Validators.required],
      nom:[this.currentUser.nom,Validators.required],
      prenom:[this.currentUser.prenom,Validators.required],
      photo:[this.currentUser.photo,Validators.required],
      tel:[this.currentUser.tel,Validators.required],
      cin:[this.currentUser.cin,Validators.required],
      cv:[this.currentUser.cv,Validators.required],
      dateInscription:[this.currentUser.dateInscription,Validators.required],
      dateNaissance:[this.currentUser.dateNaissance,Validators.required],
      diplome:[this.currentUser.diplome,Validators.required],
      email:[this.currentUser.email,Validators.required],
      type:[this.currentUser.type,Validators.required],
      encadrant:["",Validators.required],
    });
  }
  getEnseignant(){
    this.productFormGroup=this.fb.group({
      id:[this.currentUser.id,Validators.required],
      username:[this.currentUser.username,Validators.required],
      password:[this.currentUser.password,Validators.required],
      nom:[this.currentUser.nom,Validators.required],
      prenom:[this.currentUser.prenom,Validators.required],
      photo:[this.currentUser.photo,Validators.required],
      tel:[this.currentUser.tel,Validators.required],
      cin:[this.currentUser.cin,Validators.required],
      cv:[this.currentUser.cv,Validators.required],
      grade:[this.currentUser.grade,Validators.required],
      dateNaissance:[this.currentUser.dateNaissance,Validators.required],
      etablissement:[this.currentUser.etablissement,Validators.required],
      email:[this.currentUser.email,Validators.required],
      type:[this.currentUser.type,Validators.required]
    });
  }
  onUpdateEtudiant() {
    this.encadrant=this.productFormGroup.value.encadrant
    console.log(this.encadrant);
    this.productFormGroup.removeControl('encadrant');
    console.log(this.productFormGroup.value);
    this.memberService.updateEtudiant(this.productFormGroup.value,this.encadrant).subscribe(data=>{
      alert("Successfully updated");
      this.router.navigateByUrl('/');
  });
  }
  onUpdateEnseignant() {
    this.memberService.updateEnseignant(this.productFormGroup.value).subscribe(data=>{
        alert("Successfully updated");
        this.router.navigateByUrl('/');
    });
  }

}
