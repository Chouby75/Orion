import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostSpecComponent } from './post-spec.component';

describe('PostSpecComponent', () => {
  let component: PostSpecComponent;
  let fixture: ComponentFixture<PostSpecComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PostSpecComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PostSpecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
