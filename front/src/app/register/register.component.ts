import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MembresService } from '../membres.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  memberFormGroup!:FormGroup;
  submitted:boolean=false;
  errorMessage!:string;
  successMessage!:string;
  constructor(private  fb:FormBuilder, private memberService:MembresService,
              private router:Router) { }
  ngOnInit(): void {
    this.memberFormGroup=this.fb.group({
      username:["",Validators.required],
      password:["",Validators.required],
      confirmedPassword:["",Validators.required],
      type: ['', [Validators.required]]
    });
  }
  onSave() {
    this.submitted=true;
    if(this.memberFormGroup.invalid) return;
    console.log(this.memberFormGroup.value)
    if(this.memberFormGroup.get('type')?.value=="ens"){
      this.memberService.saveEnseignant(this.memberFormGroup.value).subscribe(data=>{
        this.successMessage="Inscription réussite ! Merci de se connecter."
        console.log(this.successMessage);
        this.router.navigateByUrl('/login');
      },err=>{
        this.errorMessage="Merci de vérifier vos coordonnées."
        console.log(err);
      });
    }
    
    else if(this.memberFormGroup.get('type')?.value=="etd"){
      this.memberService.saveEtudiant(this.memberFormGroup.value).subscribe(data=>{
        this.successMessage="Inscription réussite ! Merci de se connecter."
        this.router.navigateByUrl('/login');
      },err=>{
        this.errorMessage="Merci de vérifier vos coordonnées."
        console.log(err);
      });
    }    
  }
}
