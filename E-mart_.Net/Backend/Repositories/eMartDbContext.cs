using System;
using System.Collections.Generic;
using E_mart.Models;
using Microsoft.EntityFrameworkCore;
using Pomelo.EntityFrameworkCore.MySql.Scaffolding.Internal;

namespace E_mart.Repositories;

public partial class eMartDbContext : DbContext
{
    public eMartDbContext()
    {
    }

    public eMartDbContext(DbContextOptions<eMartDbContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Cart> Carts { get; set; }

    public virtual DbSet<Cartitem> Cartitems { get; set; }

    public virtual DbSet<Category> Categories { get; set; }

    public virtual DbSet<Order> Orders { get; set; }

    public virtual DbSet<PaymentDetail> PaymentDetails { get; set; }

    public virtual DbSet<Product> Products { get; set; }

    public virtual DbSet<Subcategory> Subcategories { get; set; }

    public virtual DbSet<User> Users { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. You can avoid scaffolding the connection string by using the Name= syntax to read it from configuration - see https://go.microsoft.com/fwlink/?linkid=2131148. For more guidance on storing connection strings, see https://go.microsoft.com/fwlink/?LinkId=723263.
        => optionsBuilder.UseMySql("server=localhost;database=emart;user=root;password=123456", ServerVersion.Parse("8.0.39-mysql"));

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder
            .UseCollation("utf8mb4_0900_ai_ci")
            .HasCharSet("utf8mb4");

        modelBuilder.Entity<Cart>(entity =>
        {
            entity.HasKey(e => e.Cartid).HasName("PRIMARY");

            entity.HasOne(d => d.Product).WithMany(p => p.Carts)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKf02ibgv71d0qx173h41rsjeri");

            entity.HasOne(d => d.User).WithMany(p => p.Carts)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK7omdju5h5l95oek80ymvb56uq");
        });

        modelBuilder.Entity<Cartitem>(entity =>
        {
            entity.HasKey(e => e.Cartitemid).HasName("PRIMARY");

            entity.HasOne(d => d.Cart).WithMany(p => p.Cartitems)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK5vi0udj5ia7o0h6k6r0op1do6");

            entity.HasOne(d => d.Product).WithMany(p => p.Cartitems)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKp74gufw7pdc246iolcggngpp");
        });

        modelBuilder.Entity<Category>(entity =>
        {
            entity.HasKey(e => e.Categoryid).HasName("PRIMARY");
        });

        modelBuilder.Entity<Order>(entity =>
        {
            entity.HasKey(e => e.Orderid).HasName("PRIMARY");

            entity.HasOne(d => d.Cart).WithMany(p => p.Orders)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKhwdcdaiq23cketwygk74e29c4");

            entity.HasOne(d => d.User).WithMany(p => p.Orders)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKpnm1eeupqm4tykds7k3okqegv");
        });

        modelBuilder.Entity<PaymentDetail>(entity =>
        {
            entity.HasKey(e => e.Paymentid).HasName("PRIMARY");

            entity.HasOne(d => d.Order).WithMany(p => p.PaymentDetails)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FKdfd34sf5c5xu3ralj3r11u3ih");
        });

        modelBuilder.Entity<Product>(entity =>
        {
            entity.HasKey(e => e.Productid).HasName("PRIMARY");

            entity.HasOne(d => d.Category).WithMany(p => p.Products)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK1krrsjgcawsfg8k8u4hm5gi8q");

            entity.HasOne(d => d.Subcategory).WithMany(p => p.Products).HasConstraintName("FKi5prgvmxtqxb5fji9wc2qivr2");
        });

        modelBuilder.Entity<Subcategory>(entity =>
        {
            entity.HasKey(e => e.Subcategoryid).HasName("PRIMARY");

            entity.HasOne(d => d.Category).WithMany(p => p.Subcategories)
                .OnDelete(DeleteBehavior.ClientSetNull)
                .HasConstraintName("FK5cs3pf1w7kktveo6g9o25ita6");
        });

        modelBuilder.Entity<User>(entity =>
        {
            entity.HasKey(e => e.Userid).HasName("PRIMARY");
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
