using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("categories")]
public partial class Category
{
    [Key]
    [Column("categoryid")]
    public int Categoryid { get; set; }

    [Column("category_description")]
    [StringLength(255)]
    public string? CategoryDescription { get; set; }

    [Column("categoryimage")]
    [StringLength(255)]
    public string? Categoryimage { get; set; }

    [Column("categoryname")]
    [StringLength(255)]
    public string Categoryname { get; set; } = null!;

    [Column("created_at")]
    [MaxLength(6)]
    public DateTime? CreatedAt { get; set; }

    [Column("updated_at")]
    [MaxLength(6)]
    public DateTime? UpdatedAt { get; set; }

    [InverseProperty("Category")]
    public virtual ICollection<Product> Products { get; set; } = new List<Product>();

    [InverseProperty("Category")]
    public virtual ICollection<Subcategory> Subcategories { get; set; } = new List<Subcategory>();
}
