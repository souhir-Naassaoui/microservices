import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PublicationsService } from './../publications.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Event } from '@angular/router';

@Component({
  selector: 'app-edit-event',
  templateUrl: './edit-event.component.html',
  styleUrls: ['./edit-event.component.css']
})
export class EditEventComponent implements OnInit {

  eventId: any;
 eventFormGroup?: FormGroup;
  submitted:boolean=false;
  event:any;
  constructor(private activateRoute: ActivatedRoute, private pubService: PublicationsService,
    private fb: FormBuilder) { }

  ngOnInit(): void {
    this.eventId=this.activateRoute.snapshot.params.id;
    this.pubService.getEvent(this.eventId).subscribe(
      data => {
        this.event=data;
        console.log(data);
      }
    );
  }

  onUpdateEvent(){
    console.log(this.event);
    
  }

}
