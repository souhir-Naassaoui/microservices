import { PublicationsService } from './../publications.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MembresService } from '../membres.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  categories:any;
  publications:any;
  currentCategory!:any;
  urlpub:any;
  name:any;
  auteur:any;
  idAuteur:any;
  
  constructor(private pubService:PublicationsService, private router:Router,private memberService:MembresService) { }

  ngOnInit(): void {
    this.pubService.getAllPublications()
    .subscribe(data=>{
      this.publications=data;
      this.urlpub="http://localhost:8082/publications/publicationsById/";
      this.name="?name=";
      this.auteur="http://localhost:8087/enseignants/";
    },err=>{
      console.log(err);
    })
    
  }
  onConsultPub(p:any){
    
    this.router.navigateByUrl('/publications/'+p);
  }
/*
ngOnInit(): void {
    this.pubService.getAllCategories()
    .subscribe(data=>{
      this.categories=data;
    },err=>{
      console.log(err);
    })
  }

  onGetPublications(c:any){
    this.publications=c.publications;
    console.log(this.publications);
  }*/
}
