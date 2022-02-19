import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPubComponent } from './view-pub.component';

describe('ViewPubComponent', () => {
  let component: ViewPubComponent;
  let fixture: ComponentFixture<ViewPubComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewPubComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPubComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
