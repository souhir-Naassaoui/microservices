import { MembresService } from './../membres.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-pub',
  templateUrl: './view-pub.component.html',
  styleUrls: ['./view-pub.component.css']
})
export class ViewPubComponent implements OnInit {

  id: number=0;
  publications:any;
  auteur:any;
  auteurId:any;
  urlpub:any;
  constructor(private route: ActivatedRoute,private memberService:MembresService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']; 
    console.log(this.id);
    
    this.memberService.getPublication(this.id).subscribe(data=>{
      this.publications=data;
      console.log(this.publications);
      this.auteurId=this.publications.auteurId;
      console.log(this.auteurId)
      this.getEnseignant();
    })
 
  }

  getEnseignant(){
    console.log(this.publications);
    console.log(this.publications.auteurId)
    this.memberService.getEnseignant(this.publications.auteurId).subscribe(data=>{
      this.auteur=data;
      this.urlpub="http://localhost:8082/publications/publicationsById/"+this.auteurId+"?name=";
      console.log(this.auteur)
    })
 }

}
